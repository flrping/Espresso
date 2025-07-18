package dev.flrp.espresso.storage;

import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;
import dev.flrp.espresso.storage.query.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MariaDBStorageProviderTest {

    @Container
    private static final MariaDBContainer<?> mariadbContainer = new MariaDBContainer<>("mariadb")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withExposedPorts(3306);

    private SQLStorageProvider provider;
    private final Logger logger = Logger.getLogger("TestLogger");

    @BeforeAll
    void setup() {
        String host = mariadbContainer.getHost();
        int port = mariadbContainer.getMappedPort(3306);
        String database = mariadbContainer.getDatabaseName();
        String user = mariadbContainer.getUsername();
        String password = mariadbContainer.getPassword();

        provider = new SQLStorageProvider(logger, host, port, database, user, password, StorageType.MARIADB);
        try {
            provider.open();

            List<SQLColumn> columns = new ArrayList<>();
            columns.add(new SQLColumn("id", SQLType.INT).primaryKey().autoIncrement());
            columns.add(new SQLColumn("name", SQLType.STRING));
            String query = provider.getDialect().createTable("test", columns);
            provider.query(query);
        } catch (ProviderException e) {
            logger.severe(e.getMessage());
        }
    }

    @AfterAll
    void cleanup() {
        try {
            provider.query(provider.getDialect().dropTable("test"));
            provider.query(provider.getDialect().dropTable("date"));
            provider.close();
        } catch (ProviderException e) {
            Logger.getLogger("TestLogger").severe(e.getMessage());
        }
    }

    @Test
    void testConnection() {
        assertTrue(provider.isConnected());
    }

    @Test
    void testInsertAndSelect() {
        try {
            InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
            queryBuilder.column("name", "Espresso").execute();

            SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
            selectQueryBuilder.columns("name");
            List<String> names = provider.queryMap(selectQueryBuilder.build(), rs -> rs.getString("name"));

            assertFalse(names.isEmpty());
            assertEquals("Espresso", names.get(0));

            DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
            deleteQueryBuilder.execute();
        } catch (ProviderException e) {
            logger.severe(e.getMessage());
        }
    }

    @Test
    void testTransactionRollback() {
        try {
            DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
            deleteQueryBuilder.execute();
            provider.transaction(() -> {
                InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
                queryBuilder.column("name", "ShouldFail").execute();
                throw new RuntimeException("Fail transaction");
            });
        } catch (ProviderException e) {
            logger.severe(e.getMessage());
        }

        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
        selectQueryBuilder.columns("name");
        List<String> names;
        try {
            names = provider.queryMap(selectQueryBuilder.build(), rs -> rs.getString("name"));
        } catch (ProviderException e) {
            throw new RuntimeException(e);
        }

        assertTrue(names.isEmpty());
    }

    @Test
    void testTransactionCommit() {
        try {
            provider.transaction(() -> {
                InsertQueryBuilder queryBuilder = new InsertQueryBuilder("test", provider);
                queryBuilder.column("name", "Committed").execute();
            });

            SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("test", provider);
            selectQueryBuilder.columns("name");
            List<String> names = provider.queryMap(selectQueryBuilder.build(), rs -> rs.getString("name"));

            assertFalse(names.isEmpty());
            assertEquals("Committed", names.get(0));

            DeleteQueryBuilder deleteQueryBuilder = new DeleteQueryBuilder("test", provider);
            deleteQueryBuilder.execute();
        } catch (ProviderException e) {
            logger.severe(e.getMessage());
        }
    }

    @Test
    void testTableWithDateDefault() {
        List<SQLColumn> columns = new ArrayList<>();
        columns.add(new SQLColumn("id", SQLType.INT).primaryKey().autoIncrement());
        columns.add(new SQLColumn("name", SQLType.STRING));
        columns.add(new SQLColumn("date", SQLType.DATE).defaultValue("CURRENT_DATE"));

        try {
            provider.query(provider.getDialect().createTable("date", columns));
        } catch (ProviderException e) {
            throw new RuntimeException(e);
        }
    }
}