package dev.flrp.espresso.storage;

import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLiteStorageProvider;
import dev.flrp.espresso.storage.query.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SQLiteStorageProviderTest {

    private SQLiteStorageProvider provider;
    private final Logger logger = Logger.getLogger("TestLogger");
    private File tempFile;

    @BeforeAll
    void setup() throws IOException, ProviderException {
        tempFile = File.createTempFile("test", ".db");
        provider = new SQLiteStorageProvider(logger, tempFile);
        provider.open();

        List<SQLColumn> columns = new ArrayList<>();
        columns.add(new SQLColumn("id", ColumnType.INT)
                .primaryKey()
                .autoIncrement());
        columns.add(new SQLColumn("name", ColumnType.STRING)
                .notNull());
        columns.add(new SQLColumn("age", ColumnType.INT)
                .defaultValue("20")
                .notNull());
        columns.add(new SQLColumn("department", ColumnType.STRING).check("department IN ('IT', 'HR', 'Sales')"));
        columns.add(new SQLColumn("created_at", ColumnType.TIMESTAMP)
                .defaultValue("CURRENT_TIMESTAMP")
                .notNull());
        columns.add(new SQLColumn("updated_at", ColumnType.TIMESTAMP)
                .defaultValue("CURRENT_TIMESTAMP")
                .notNull());

        String query = provider.getDialect().createTable("test", columns);
        provider.query(query);
    }

    @AfterAll
    void cleanup() throws ProviderException {
        provider.close();
        tempFile.delete();
    }

    @BeforeEach
    void resetTable() throws ProviderException {
        provider.query(DeleteQueryBuilder.with("test", provider));
    }

    @Test
    void testConnection() {
        assertTrue(provider.isConnected());
    }

    @Test
    void testAllQueriesWithBuild() throws ProviderException {
        // Insert
        InsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 20)
                .column("department", "IT")
                .execute();

        // Update
        UpdateQueryBuilder.with("test", provider)
                .set("name", "Espresso")
                .where("age = ?", 20)
                .execute();

        // Upsert
        UpsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 22)
                .column("department", "Sales")
                .conflict("id")
                .execute();

        // Select
        List<TestEmployee> employees = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*").build(),
                rs -> new TestEmployee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")));

        TestEmployee employee = employees.get(0);
        assertEquals(1, employees.size());
        assertEquals("Espresso", employee.name);
        assertEquals(22, employee.age);
        assertEquals("Sales", employee.department);

        // Delete
        DeleteQueryBuilder.with("test", provider)
                .where("name = ?", employee.name)
                .execute();

        // Verify empty
        List<String> names = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*").build(), rs -> rs.getString("name"));
        assertTrue(names.isEmpty());
    }

    @Test
    void testAllQueriesWithParameters() throws ProviderException {
        // Insert
        InsertQueryBuilder insert = InsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 20)
                .column("department", "IT");
        provider.query(insert.build(), insert.getParameters());

        // Update
        UpdateQueryBuilder update = UpdateQueryBuilder.with("test", provider)
                .set("name", "Espresso")
                .where("age = ?", 20);
        provider.query(update.build(), update.getParameters());

        // Upsert
        UpsertQueryBuilder upsert = UpsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 22)
                .column("department", "Sales")
                .conflict("id");
        provider.query(upsert.build(), upsert.getParameters());

        // Select
        List<TestEmployee> employees = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*").build(),
                rs -> new TestEmployee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")));

        TestEmployee employee = employees.get(0);
        assertEquals(1, employees.size());
        assertEquals("Espresso", employee.name);
        assertEquals(22, employee.age);
        assertEquals("Sales", employee.department);

        // Select with condition
        SelectQueryBuilder conditional = SelectQueryBuilder.with("test", provider)
                .columns("name")
                .where("age > ?", 20);
        List<String> names = provider.queryMap(
                conditional.build(),
                conditional.getParameters(),
                rs -> rs.getString("name"));

        assertEquals(1, names.size());
        assertEquals("Espresso", names.get(0));

        // Delete
        DeleteQueryBuilder delete = DeleteQueryBuilder.with("test", provider).where("name = ?", employee.name);
        provider.query(delete.build(), delete.getParameters());

        // Verify empty
        List<String> cleared = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*").build(), rs -> rs.getString("name")
        );
        assertTrue(cleared.isEmpty());
    }

    @Test
    void testAllQueriesWithBuilder() throws ProviderException {
        // Insert
        InsertQueryBuilder insert = InsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 20)
                .column("department", "IT");
        provider.query(insert);

        // Update
        UpdateQueryBuilder update = UpdateQueryBuilder.with("test", provider)
                .set("name", "Espresso")
                .where("age = ?", 20);
        provider.query(update);

        // Upsert
        UpsertQueryBuilder upsert = UpsertQueryBuilder.with("test", provider)
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 22)
                .column("department", "Sales")
                .conflict("id");
        provider.query(upsert);

        // Select
        List<TestEmployee> employees = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*"),
                rs -> new TestEmployee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")));

        TestEmployee employee = employees.get(0);
        assertEquals(1, employees.size());
        assertEquals("Espresso", employee.name);
        assertEquals(22, employee.age);
        assertEquals("Sales", employee.department);

        // Delete
        DeleteQueryBuilder delete = DeleteQueryBuilder.with("test", provider).where("name = ?", employee.name);
        provider.query(delete);

        // Verify empty
        List<String> cleared = provider.queryMap(
                SelectQueryBuilder.with("test", provider).columns("*"), rs -> rs.getString("name")
        );
        assertTrue(cleared.isEmpty());
    }

    @Test
    void testAllExecutionNotAllowed() {
        // Insert
        InsertQueryBuilder insert = InsertQueryBuilder.with("test", provider.getType())
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 20)
                .column("department", "IT");
        assertThrows(UnsupportedOperationException.class, insert::execute);

        // Upsert
        UpsertQueryBuilder upsert = UpsertQueryBuilder.with("test", provider.getType())
                .column("id", 1)
                .column("name", "Espresso")
                .column("age", 22)
                .column("department", "Sales")
                .conflict("id");
        assertThrows(UnsupportedOperationException.class, upsert::execute);

        // Update
        UpdateQueryBuilder update = UpdateQueryBuilder.with("test", provider.getType())
                .set("name", "Espresso")
                .where("age = ?", 20);
        assertThrows(UnsupportedOperationException.class, update::execute);

        // Delete
        DeleteQueryBuilder delete = DeleteQueryBuilder.with("test", provider.getType())
                .where("name = ?", "Espresso");
        assertThrows(UnsupportedOperationException.class, delete::execute);

        // Select does not have any execution methods.
    }

}
