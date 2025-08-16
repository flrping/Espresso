package dev.flrp.espresso.storage.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;

/**
 * A builder for constructing SQL UPSERT queries. This class allows you to
 * specify the table to insert into or update the columns with their values, and
 * the conflict columns for handling duplicates.
 *
 * If a storage provider is not provided, the builder will be configured for
 * string only returns.
 */
public class UpsertQueryBuilder implements QueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private final boolean stringOnly;

    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();
    private final List<String> placeholders = new ArrayList<>();
    private List<String> conflictColumns = new ArrayList<>();

    private UpsertQueryBuilder(String table, SQLStorageDialect dialect, SQLStorageBehavior behavior, boolean stringOnly) {
        this.table = table;
        this.dialect = dialect;
        this.behavior = behavior;
        this.stringOnly = stringOnly;
    }

    /**
     * Factory method for using an SQLStorageProvider.
     *
     * @param table The table to upsert into.
     * @param provider The storage provider to use.
     * @return A new UpsertQueryBuilder instance.
     */
    public static UpsertQueryBuilder with(String table, SQLStorageProvider provider) {
        return new UpsertQueryBuilder(table, provider.getDialect(), (SQLStorageBehavior) provider.getBehavior(), false);
    }

    /**
     * Factory method for building SQL strings only.
     *
     * @param table The table to upsert into.
     * @param type The storage type to use.
     * @return A new UpsertQueryBuilder instance.
     */
    public static UpsertQueryBuilder with(String table, StorageType type) {
        return new UpsertQueryBuilder(table, (SQLStorageDialect) type.getDialect(), null, true);
    }

    /**
     * Adds a column and its corresponding value to the UPSERT query.
     *
     * @param name The name of the column.
     * @param value The value to be inserted or updated in the column.
     * @return The current instance of UpsertQueryBuilder for method chaining.
     */
    public UpsertQueryBuilder column(String name, Object value) {
        columns.add(name);
        values.add(value);
        placeholders.add("?");
        return this;
    }

    /**
     * Adds a column and its corresponding value to the UPSERT query. Casts enum
     * values.
     *
     * @param name The name of the column.
     * @param value The value to be inserted or updated in the column.
     * @param enumName The name of the enum type.
     */
    public UpsertQueryBuilder column(String name, Object value, String enumName) {
        columns.add(name);
        values.add(value);
        placeholders.add("?::" + enumName);
        return this;
    }

    /**
     * Specifies the conflict columns for the UPSERT query. These columns are
     * used to determine if an existing row should be updated or a new row
     * should be inserted. At least one conflict column must be specified.
     *
     * @param columns The names of the columns that define conflicts.
     * @return The current instance of UpsertQueryBuilder for method chaining.
     */
    public UpsertQueryBuilder conflict(String... columns) {
        conflictColumns = new ArrayList<>(Arrays.asList(columns));
        return this;
    }

    /**
     * Executes the UPSERT query against the database.
     *
     * @throws ProviderException If an error occurs during query execution.
     * @throws UnsupportedOperationException If the builder is configured for
     * string only returns.
     */
    public void execute() throws ProviderException, UnsupportedOperationException {
        if (stringOnly) {
            throw new UnsupportedOperationException("Cannot execute a bare UPSERT query with a string only builder.");
        }
        behavior.query(this);
    }

    /**
     * Returns the parameters used in the UPSERT query.
     *
     * @return A list of parameters for the UPSERT query.
     */
    @Override
    public List<Object> getParameters() {
        return values;
    }

    /**
     * Builds the SQL UPSERT query string.
     *
     * @return The SQL UPSERT query as a String.
     * @throws IllegalStateException If no conflict columns are specified.
     */
    @Override
    public String build() {
        if (conflictColumns == null || conflictColumns.isEmpty()) {
            throw new IllegalStateException("UpsertQueryBuilder requires at least one conflict column.");
        }
        return dialect.upsert(table, columns, placeholders, conflictColumns);
    }

}
