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
 * A builder for constructing SQL DELETE queries. This class allows you to
 * specify the table to delete from and the conditions for deletion.
 * <p>
 * If a storage provider is not provided, the builder will be configured for
 * string only returns.
 */
public class DeleteQueryBuilder implements QueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private final boolean stringOnly;

    private String whereClause;
    private List<Object> whereParams = Collections.emptyList();

    private DeleteQueryBuilder(String table, SQLStorageDialect dialect, SQLStorageBehavior behavior, boolean stringOnly) {
        this.table = table;
        this.dialect = dialect;
        this.behavior = behavior;
        this.stringOnly = stringOnly;
    }

    /**
     * Factory method for using an SQLStorageProvider (allows execution).
     *
     * @param table    The table to delete from.
     * @param provider The storage provider to use.
     * @return A new DeleteQueryBuilder instance.
     */
    public static DeleteQueryBuilder with(String table, SQLStorageProvider provider) {
        return new DeleteQueryBuilder(table, provider.getDialect(), (SQLStorageBehavior) provider.getBehavior(), false);
    }

    /**
     * Factory method for building SQL strings only (no execution).
     *
     * @param table The table to delete from.
     * @param type  The storage type to use.
     * @return A new DeleteQueryBuilder instance.
     */
    public static DeleteQueryBuilder with(String table, StorageType type) {
        return new DeleteQueryBuilder(table, (SQLStorageDialect) type.getDialect(), null, true);
    }

    /**
     * Sets the WHERE clause for the DELETE query. The clause must be a valid
     * SQL WHERE condition with proper '?' usage.
     *
     * @param clause The SQL WHERE clause.
     * @param params The parameters to be used in the WHERE clause.
     * @return The current instance of DeleteQueryBuilder for method chaining.
     */
    public DeleteQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    /**
     * Executes the DELETE query against the database.
     *
     * @throws ProviderException             If an error occurs during query execution.
     * @throws UnsupportedOperationException If the builder is configured for
     *                                       string only returns.
     */
    public void execute() throws ProviderException, UnsupportedOperationException {
        if (stringOnly) {
            throw new UnsupportedOperationException("Cannot execute a bare DELETE query with a string only builder.");
        }
        behavior.query(this);
    }

    /**
     * Returns the parameters used in the WHERE clause.
     *
     * @return A list of parameters for the WHERE clause.
     */
    @Override
    public List<Object> getParameters() {
        return whereParams;
    }

    /**
     * Builds the SQL DELETE query string.
     *
     * @return The SQL DELETE query as a String.
     */
    @Override
    public String build() {
        return dialect.delete(table, whereClause);
    }

}
