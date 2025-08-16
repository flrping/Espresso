package dev.flrp.espresso.storage.query;

import java.util.ArrayList;
import java.util.List;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;

/**
 * A builder for constructing SQL INSERT queries. This class allows you to
 * specify the table to insert into and the columns with their values.
 *
 * If a storage provider is not provided, the builder will be configured for
 * string only returns.
 */
public class InsertQueryBuilder implements QueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private final boolean stringOnly;

    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();
    private final List<String> placeholders = new ArrayList<>();

    private InsertQueryBuilder(
            String table,
            SQLStorageDialect dialect,
            SQLStorageBehavior behavior,
            boolean stringOnly
    ) {
        this.table = table;
        this.dialect = dialect;
        this.behavior = behavior;
        this.stringOnly = stringOnly;
    }

    /**
     * Factory method for using an SQLStorageProvider (allows execution).
     *
     * @param table The table to insert into.
     * @param provider The storage provider to use.
     * @return A new InsertQueryBuilder instance.
     */
    public static InsertQueryBuilder with(String table, SQLStorageProvider provider) {
        return new InsertQueryBuilder(table, provider.getDialect(), (SQLStorageBehavior) provider.getBehavior(), false);
    }

    /**
     * Factory method for building SQL strings only (no execution).
     *
     * @param table The table to insert into.
     * @param type The storage type to use.
     * @return A new InsertQueryBuilder instance.
     */
    public static InsertQueryBuilder with(String table, StorageType type) {
        return new InsertQueryBuilder(table, (SQLStorageDialect) type.getDialect(), null, true);
    }

    /**
     * Adds a column and its corresponding value to the INSERT query.
     *
     * @param name The name of the column.
     * @param value The value to be inserted into the column.
     * @return The current instance of InsertQueryBuilder for method chaining.
     */
    public InsertQueryBuilder column(String name, Object value) {
        columns.add(name);
        values.add(value);
        placeholders.add("?");
        return this;
    }

    /**
     * Adds a column and its corresponding value to the INSERT query. Casts enum
     * values.
     *
     * @param name The name of the column.
     * @param value The value to be inserted into the column.
     * @return The current instance of InsertQueryBuilder for method chaining.
     */
    public InsertQueryBuilder column(String name, Object value, String enumName) {
        columns.add(name);
        values.add(value);
        placeholders.add("?::" + enumName);
        return this;
    }

    /**
     * Executes the INSERT query against the database.
     *
     * @throws ProviderException If an error occurs during query execution.
     * @throws UnsupportedOperationException If the builder is configured for
     * string only returns.
     */
    public void execute() throws ProviderException, UnsupportedOperationException {
        if (stringOnly) {
            throw new UnsupportedOperationException("Cannot execute a bare INSERT query with a string only builder.");
        }
        behavior.query(this);
    }

    /**
     * Returns the parameters used in the INSERT query.
     *
     * @return A list of parameters for the INSERT query.
     */
    @Override
    public List<Object> getParameters() {
        return values;
    }

    /**
     * Builds the SQL INSERT query string.
     *
     * @return The SQL INSERT query as a String.
     */
    @Override
    public String build() {
        return dialect.insert(table, columns, placeholders);
    }

}
