package dev.flrp.espresso.storage.query;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A builder for constructing SQL UPDATE queries. This class allows you to
 * specify the table to update, the columns to set, and the conditions for the
 * update.
 * <p>
 * If a storage provider is not provided, the builder will be configured for
 * string only returns.
 */
public class UpdateQueryBuilder implements QueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private final boolean stringOnly;

    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();
    private String whereClause;
    private List<Object> whereParams = Collections.emptyList();

    private UpdateQueryBuilder(String table, SQLStorageDialect dialect, SQLStorageBehavior behavior, boolean stringOnly) {
        this.table = table;
        this.dialect = dialect;
        this.behavior = behavior;
        this.stringOnly = stringOnly;
    }

    /**
     * Factory method for using an SQLStorageProvider (allows execution).
     *
     * @param table    The table to update.
     * @param provider The storage provider to use.
     * @return A new UpdateQueryBuilder instance.
     */
    public static UpdateQueryBuilder with(String table, SQLStorageProvider provider) {
        return new UpdateQueryBuilder(table, provider.getDialect(), (SQLStorageBehavior) provider.getBehavior(), false);
    }

    /**
     * Factory method for building SQL strings only (no execution).
     *
     * @param table The table to update.
     * @param type  The storage type to use.
     * @return A new UpdateQueryBuilder instance.
     */
    public static UpdateQueryBuilder with(String table, StorageType type) {
        return new UpdateQueryBuilder(table, (SQLStorageDialect) type.getDialect(), null, true);
    }

    /**
     * Adds a column and its corresponding value to the UPDATE query.
     *
     * @param column The name of the column to update.
     * @param value  The value to set for the column.
     * @return The current instance of UpdateQueryBuilder for method chaining.
     */
    public UpdateQueryBuilder set(String column, Object value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    /**
     * Sets the WHERE clause for the UPDATE query. The clause must be a valid
     * SQL WHERE condition with proper '?' usage.
     *
     * @param clause The SQL WHERE clause.
     * @param params The parameters to be used in the WHERE clause.
     * @return The current instance of UpdateQueryBuilder for method chaining.
     */
    public UpdateQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    /**
     * Executes the UPDATE query against the database.
     *
     * @throws ProviderException             If an error occurs during query execution.
     * @throws UnsupportedOperationException If the builder is configured for
     *                                       string only returns.
     */
    public void execute() throws ProviderException, UnsupportedOperationException {
        if (stringOnly) {
            throw new UnsupportedOperationException("Cannot execute a bare UPDATE query with a string only builder.");
        }
        behavior.query(this);
    }

    /**
     * Returns the parameters used in the UPDATE query.
     *
     * @return A list of parameters for the UPDATE query, including values and
     * where clause parameters in their respective order.
     */
    @Override
    public List<Object> getParameters() {
        List<Object> params = new ArrayList<>(values);
        if (whereParams != null && !whereParams.isEmpty()) {
            params.addAll(whereParams);
        }
        return params;
    }

    /**
     * Builds the SQL UPDATE query string.
     *
     * @return The SQL UPDATE query as a String.
     */
    @Override
    public String build() {
        return dialect.update(table, columns, whereClause);
    }

}
