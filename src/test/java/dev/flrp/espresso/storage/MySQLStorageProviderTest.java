package dev.flrp.espresso.storage;

import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;
import dev.flrp.espresso.storage.query.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MySQLStorageProviderTest {

    private SQLStorageProvider provider;

    @BeforeAll
    void setup() {
        Logger logger = Logger.getLogger("TestLogger");

        String host = "localhost";
        int port = 3306;
        String user = "test";
        String password = "test";

        provider = new SQLStorageProvider(logger, host, port, "test", user, password, StorageType.MYSQL);
        provider.open();

        List<SQLColumn> columns = new ArrayList<>();
        columns.add(new SQLColumn("id", SQLType.INT).primaryKey().autoIncrement());
        columns.add(new SQLColumn("name", SQLType.STRING));
        String query = provider.getDialect().createTable("test", columns);
        provider.query(query);
    }

    @AfterAll
    void cleanup() {
        provider.query(provider.getDialect().dropTable("test"));
        provider.close();
    }

    @Test
    void testConnection() {
        assertTrue(provider.isConnected());
    }

    @Test
    void testInsertAndSelect() {
        InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
        queryBuilder.column("name", "Espresso");
        queryBuilder.execute();

        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
        selectQueryBuilder.columns("name");
        List<String> names = provider.query(selectQueryBuilder.build(), rs -> {
            try {
                return rs.getString("name");
            } catch (Exception e) {
                return null;
            }
        });

        assertFalse(names.isEmpty());
        assertEquals("Espresso", names.get(0));

        DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
        deleteQueryBuilder.execute();
    }

    @Test
    void testTransactionRollback() {
        DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
        deleteQueryBuilder.execute();

        try {
            provider.transaction(() -> {
                InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
                queryBuilder.column("name", "ShouldFail");
                provider.query(queryBuilder.build());
                throw new RuntimeException("Fail transaction");
            });
        } catch (Exception ignored) {}

        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
        selectQueryBuilder.columns("name");
        List<String> names = provider.query(selectQueryBuilder.build(), rs -> {
            try {
                return rs.getString("name");
            } catch (Exception e) {
                return null;
            }
        });

        assertTrue(names.isEmpty());
    }

    @Test
    void testTransactionCommit() {
        provider.transaction(() -> {
            InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
            queryBuilder.column("name", "Committed");
            queryBuilder.execute();
        });

        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
        selectQueryBuilder.columns("name");
        List<String> names = provider.query(selectQueryBuilder.build(), rs -> {
            try {
                return rs.getString("name");
            } catch (Exception e) {
                return null;
            }
        });

        DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
        deleteQueryBuilder.execute();
    }
}