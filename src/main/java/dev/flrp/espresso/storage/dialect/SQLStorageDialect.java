package dev.flrp.espresso.storage.dialect;

import java.util.List;
import java.util.stream.Collectors;

import dev.flrp.espresso.storage.query.SQLColumn;

public abstract class SQLStorageDialect implements StorageDialect {

    /**
     * Inserts the columns into the table.
     *
     * @param table   The table
     * @param columns The columns
     */
    public String insert(String table, List<String> columns) {
        List<String> placeholders = columns.stream().map(col -> "?").collect(Collectors.toList());
        return insert(table, columns, placeholders);
    }

    /**
     * Inserts the columns into the table with placeholders.
     *
     * @param table        The table
     * @param columns      The columns
     * @param placeholders The placeholders
     */
    public String insert(String table, List<String> columns, List<String> placeholders) {
        String cols = String.join(", ", columns);
        String phs = String.join(", ", placeholders);
        return "INSERT INTO " + quoteIdentifier(table) + " (" + cols + ") VALUES (" + phs + ")";
    }

    /**
     * Updates the specified columns in the table with new values based on the where
     * clause.
     *
     * @param table       The name of the table to update.
     * @param columns     The columns to be updated with new values.
     * @param whereClause The condition to determine which rows to update.
     * @return A SQL string for updating rows in the table.
     */
    public String update(String table, List<String> columns, String whereClause) {
        List<String> placeholders = columns.stream().map(c -> "?").collect(Collectors.toList());
        return update(table, columns, placeholders, whereClause);
    }

    /**
     * Updates the specified columns in the table with new values based on the where
     * clause.
     *
     * @param table       The name of the table to update.
     * @param columns     The columns to be updated with new values.
     * @param whereClause The condition to determine which rows to update.
     * @return A SQL string for updating rows in the table.
     */
    public String update(String table, List<String> columns, List<String> placeholders, String whereClause) {
        StringBuilder assignments = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0)
                assignments.append(", ");
            assignments.append(columns.get(i)).append(" = ").append(placeholders.get(i));
        }

        StringBuilder sql = new StringBuilder("UPDATE ").append(quoteIdentifier(table)).append(" SET ")
                .append(assignments);

        if (whereClause != null && !whereClause.isEmpty()) {
            sql.append(" WHERE ").append(whereClause);
        }

        return sql.toString();
    }

    /**
     * Creates a SQL string for an upsert operation.
     *
     * @param table           The name of the table.
     * @param columns         The columns to insert or update.
     * @param conflictColumns The columns that define conflict (usually primary or
     *                        unique key).
     * @return A SQL string for upsert.
     */
    abstract public String upsert(String table, List<String> columns, List<String> conflictColumns);

    /**
     * Creates a SQL string for an upsert operation.
     *
     * @param table           The name of the table.
     * @param columns         The columns to insert or update.
     * @param placeholders    The placeholders for the columns.
     * @param conflictColumns The columns that define conflict (usually primary or
     *                        unique key).
     * @return A SQL string for upsert.
     */
    abstract public String upsert(String table, List<String> columns, List<String> placeholders, List<String> conflictColumns);

    /**
     * Creates a SQL string for selecting columns from a table.
     *
     * @param table       The name of the table to query.
     * @param columns     The columns to select from the table.
     * @param joins       The joins to perform on the query.
     * @param whereClause The condition to determine which rows to select.
     * @param orderBy     The order to sort the rows in.
     * @param limit       The limit on the number of rows to select.
     * @return A SQL string for selecting columns from the table.
     */
    public String select(String table, List<String> columns, List<String> joins, String whereClause, String orderBy,
            String limit) {
        String cols = (columns == null || columns.isEmpty()) ? "*" : String.join(", ", columns);
        StringBuilder sql = new StringBuilder("SELECT " + cols + " FROM " + quoteIdentifier(table));

        for (String join : joins) {
            sql.append(" ").append(join);
        }

        if (whereClause != null && !whereClause.isEmpty())
            sql.append(" WHERE ").append(whereClause);
        if (orderBy != null && !orderBy.isEmpty())
            sql.append(" ORDER BY ").append(orderBy);
        if (limit != null && !limit.isEmpty())
            sql.append(" LIMIT ").append(limit);

        return sql.toString();
    }

    /**
     * Creates a SQL string for deleting rows from a table.
     *
     * @param table       The name of the table to modify.
     * @param whereClause The condition to determine which rows to delete.
     * @return A SQL string for deleting rows from the table.
     */
    public String delete(String table, String whereClause) {
        StringBuilder sql = new StringBuilder("DELETE FROM " + quoteIdentifier(table));
        if (whereClause != null && !whereClause.isEmpty()) {
            sql.append(" WHERE ").append(whereClause);
        }
        return sql.toString();
    }

    /**
     * Creates a SQL string for creating a table.
     *
     * @param table   The name of the table to create.
     * @param columns The columns to create in the table.
     * @return A SQL string for creating a table.
     */
    public String createTable(String table, List<SQLColumn> columns) {
        if (columns == null || columns.isEmpty()) {
            throw new IllegalArgumentException("Table must have at least one column");
        }
        List<String> columnDefs = columns.stream()
                .map(this::columnDefinition)
                .collect(Collectors.toList());
        return "CREATE TABLE IF NOT EXISTS " + quoteIdentifier(table) + " (" + String.join(", ", columnDefs) + ")";
    }

    /**
     * Creates a SQL string for dropping a table.
     *
     * @param table The name of the table to drop.
     * @return A SQL string for dropping a table.
     */
    public String dropTable(String table) {
        return "DROP TABLE IF EXISTS " + quoteIdentifier(table);
    }

    /**
     * Returns the options for a column type.
     * 
     * @param column The column to build the options for.
     * @return A SQL string for the column options.
     */
    public String buildTypeOptions(SQLColumn column) {
        if (column.getOptions().isEmpty())
            return "";
        StringBuilder sb = new StringBuilder("(");
        String optionsSql = column.getOptions().stream()
                .map(Object::toString)
                .map(val -> "'" + val.replace("'", "''") + "'")
                .collect(Collectors.joining(", "));

        sb.append(optionsSql);
        sb.append(")");

        return sb.toString();
    }

}
