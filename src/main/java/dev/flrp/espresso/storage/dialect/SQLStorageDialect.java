package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.SQLColumn;

import java.util.List;
import java.util.stream.Collectors;

public interface SQLStorageDialect extends StorageDialect {

    /**
     * Inserts the columns into the table.
     *
     * @param table   The table
     * @param columns The columns
     */
    default String insert(String table, List<String> columns) {
        String cols = String.join(", ", columns);
        String placeholders = String.join(", ", columns.stream().map(c -> "?").toArray(String[]::new));
        return "INSERT INTO " + table + " (" + cols + ") VALUES (" + placeholders + ")";
    }

    /**
     * Updates the specified columns in the table with new values based on the where clause.
     *
     * @param table       The name of the table to update.
     * @param columns     The columns to be updated with new values.
     * @param whereClause The condition to determine which rows to update.
     * @return A SQL string for updating rows in the table.
     */
    default String update(String table, List<String> columns, String whereClause) {
        StringBuilder assignments = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) {
                assignments.append(", ");
            }
            assignments.append(columns.get(i)).append(" = ?");
        }
        StringBuilder sql = new StringBuilder("UPDATE ").append(table).append(" SET ").append(assignments);
        if (whereClause != null && !whereClause.isEmpty()) {
            sql.append(" WHERE ").append(whereClause);
        }
        return sql.toString();
    }

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
    default String select(String table, List<String> columns, List<String> joins, String whereClause, String orderBy, String limit) {
        String cols = (columns == null || columns.isEmpty()) ? "*" : String.join(", ", columns);
        StringBuilder sql = new StringBuilder("SELECT " + cols + " FROM " + quoteIdentifier(table));

        for (String join : joins) {
            sql.append(" ").append(join);
        }

        if (whereClause != null && !whereClause.isEmpty()) sql.append(" WHERE ").append(whereClause);
        if (orderBy != null && !orderBy.isEmpty()) sql.append(" ORDER BY ").append(orderBy);
        if (limit != null && !limit.isEmpty()) sql.append(" LIMIT ").append(limit);

        return sql.toString();
    }

    /**
     * Creates a SQL string for deleting rows from a table.
     *
     * @param table       The name of the table to modify.
     * @param whereClause The condition to determine which rows to delete.
     * @return A SQL string for deleting rows from the table.
     */
    default String delete(String table, String whereClause) {
        StringBuilder sql = new StringBuilder("DELETE FROM " + table);
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
    default String createTable(String table, List<SQLColumn> columns) {
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
    default String dropTable(String table) {
        return "DROP TABLE IF EXISTS " + table;
    }

}
