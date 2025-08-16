package dev.flrp.espresso.storage.query;

/**
 * Enum representing the different types of columns that can be used in a database.
 * Each type corresponds to a specific data type that can be stored in a column.
 */

public enum ColumnType {

    UUID,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    DECIMAL,
    STRING,
    TEXT,
    ENUM,
    JSON,
    JSONB,
    BOOLEAN,
    BLOB,
    BYTE,
    NULL,
    DATE,
    TIME,
    DATETIME,
    TIMESTAMP

}

