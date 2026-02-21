package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.ColumnType;
import dev.flrp.espresso.storage.query.SQLColumn;

import java.util.List;
import java.util.stream.Collectors;

public class SQLiteStorageDialect extends SQLStorageDialect {

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> conflictColumns) {
        String insert = insert(table, columns);
        String conflict = String.join(", ", conflictColumns);
        String updates = columns.stream()
                .map(col -> col + " = EXCLUDED." + col)
                .collect(Collectors.joining(", "));

        return insert + " ON CONFLICT (" + conflict + ") DO UPDATE SET " + updates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> placeholders, List<String> conflictColumns) {
        String insert = insert(table, columns, placeholders);
        String conflict = String.join(", ", conflictColumns);
        String updates = columns.stream()
                .map(col -> col + " = EXCLUDED." + col)
                .collect(Collectors.joining(", "));

        return insert + " ON CONFLICT (" + conflict + ") DO UPDATE SET " + updates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String autoIncrement() {
        return "AUTOINCREMENT";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String quoteIdentifier(String identifier) {
        return "\"" + identifier + "\"";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(ColumnType type) {
        switch (type) {
            case NULL:
            case UUID:
            case ENUM:
            case JSON:
            case JSONB:
                return false;
            default:
                return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String columnDefinition(SQLColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(quoteIdentifier(column.getName())).append(" ");
        sb.append(mapColumnType(column));

        if (column.isPrimaryKey()) {
            sb.append(" PRIMARY KEY");
            if (column.isAutoIncrement()) {
                if (column.getType() == ColumnType.INT || column.getType() == ColumnType.LONG
                        || column.getType() == ColumnType.BOOLEAN) {
                    sb.append(" AUTOINCREMENT");
                } else {
                    throw new IllegalArgumentException(
                            "AUTOINCREMENT can only be applied to INTEGER PRIMARY KEY columns in SQLite");
                }
            }
        }

        if (column.isUnique())
            sb.append(" UNIQUE");
        if (column.isNotNull())
            sb.append(" NOT NULL");
        if (column.getDefaultValue() != null) {
            String defaultValue = column.getDefaultValue();
            boolean isString = column.getType() == ColumnType.STRING;
            boolean isSQLFunction = defaultValue.matches("(?i)CURRENT_(DATE|TIME|TIMESTAMP)");
            if (isString && !isSQLFunction && !(defaultValue.startsWith("'") && defaultValue.endsWith("'"))) {
                defaultValue = "'" + defaultValue + "'";
            }
            sb.append(" DEFAULT ").append(defaultValue);
        }
        if (column.getCheckConstraint() != null)
            sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");

        return sb.toString();
    }

    public String mapColumnType(SQLColumn column) {
        switch (column.getType()) {
            case SHORT:
            case INT:
            case BOOLEAN:
            case LONG:
                return "INTEGER";
            case STRING:
            case TEXT:
                return "TEXT";
            case DOUBLE:
            case FLOAT:
            case DECIMAL:
                return "REAL";
            case BLOB:
            case BYTE:
                return "BLOB";
            case DATE:
                return "DATE";
            case TIME:
                return "TIME";
            case DATETIME:
            case TIMESTAMP:
                return "DATETIME";
            default:
                throw new UnsupportedOperationException("Unsupported column type: " + column.getType());
        }
    }

}
