package dev.flrp.espresso.storage.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;
import dev.flrp.espresso.storage.provider.StorageType;

/**
 * A builder for constructing SQL SELECT queries. This class allows you to
 * specify the table to select from, the columns to retrieve, and various
 * clauses such as WHERE, JOIN, ORDER BY, and LIMIT.
 */
public class SelectQueryBuilder implements QueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;

    private List<String> columns = new ArrayList<>();
    private final List<String> joins = new ArrayList<>();
    private String whereClause = null;
    private List<Object> whereParams = new ArrayList<>();
    private String orderByClause = null;
    private String limitClause = null;

    private SelectQueryBuilder(String table, SQLStorageDialect dialect) {
        this.table = table;
        this.dialect = dialect;
    }

    /**
     * Factory method for using an SQLStorageProvider.
     *
     * @param table The table to select from.
     * @param provider The storage provider to use.
     * @return A new SelectQueryBuilder instance.
     */
    public static SelectQueryBuilder with(String table, SQLStorageProvider provider) {
        return new SelectQueryBuilder(table, provider.getDialect());
    }

    /**
     * Factory method for building SQL strings only.
     *
     * @param table The table to select from.
     * @param type The storage type to use.
     * @return A new SelectQueryBuilder instance.
     */
    public static SelectQueryBuilder with(String table, StorageType type) {
        return new SelectQueryBuilder(table, (SQLStorageDialect) type.getDialect());
    }

    /**
     * Sets the columns to be selected in the query. If no columns are
     * specified, the dialect would likely assume to select all columns (`*`).
     * It is recommended to specify (`*`) if you want to select all columns
     * explicitly.
     *
     * @param cols The names of the columns to select.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder columns(String... cols) {
        this.columns = new ArrayList<>(Arrays.asList(cols));
        return this;
    }

    /**
     * Sets the WHERE clause for the SELECT query. The clause must be a valid
     * SQL WHERE condition with proper '?' usage.
     *
     * @param clause The SQL WHERE clause.
     * @param params The parameters to be used in the WHERE clause.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    /**
     * Adds a JOIN clause to the SELECT query.
     *
     * @param joinClause The JOIN clause to be added.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder join(String joinClause) {
        joins.add("JOIN " + joinClause);
        return this;
    }

    /**
     * Adds a LEFT JOIN clause to the SELECT query.
     *
     * @param joinClause The LEFT JOIN clause to be added.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder leftJoin(String joinClause) {
        joins.add("LEFT JOIN " + joinClause);
        return this;
    }

    /**
     * Adds a RIGHT JOIN clause to the SELECT query.
     *
     * @param joinClause The RIGHT JOIN clause to be added.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder rightJoin(String joinClause) {
        joins.add("RIGHT JOIN " + joinClause);
        return this;
    }

    /**
     * Sets the ORDER BY clause for the SELECT query.
     *
     * @param orderByClause The SQL ORDER BY clause.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder orderBy(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    /**
     * Sets the LIMIT clause for the SELECT query. This is used to limit the
     * number of rows returned by the query.
     *
     * @param limitClause The SQL LIMIT clause.
     * @return The current instance of SelectQueryBuilder for method chaining.
     */
    public SelectQueryBuilder limit(String limitClause) {
        this.limitClause = limitClause;
        return this;
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
     * Builds the SQL SELECT query string. This method constructs the query
     * based on the specified table, columns, joins, where clause, order by
     * clause, and limit clause.
     *
     * @return The SQL SELECT query as a String.
     */
    @Override
    public String build() {
        return dialect.select(table, columns, joins, whereClause, orderByClause, limitClause);
    }

}
