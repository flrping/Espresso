package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.SQLColumn;

public interface StorageDialect {


    /**
     * Creates a SQL string for auto incrementing a column.
     *
     * @return A SQL string for auto incrementing a column.
     */
    String autoIncrement();

    /**
     * Creates a SQL string for quoting an identifier.
     *
     * @param identifier The identifier to quote.
     * @return A SQL string for quoting the identifier.
     */
    String quoteIdentifier(String identifier);

    /**
     * Creates a SQL string for a column definition.
     *
     * @param column The column to define.
     * @return A SQL string for the column definition.
     */
    String columnDefinition(SQLColumn column);

}
