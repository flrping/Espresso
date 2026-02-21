package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.ColumnType;
import dev.flrp.espresso.storage.query.SQLColumn;

import java.util.List;
import java.util.stream.Collectors;

public class MariaDBStorageDialect extends SQLStorageDialect {

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> conflictColumns) {
        String insert = insert(table, columns);
        String updates = columns.stream()
                .filter(col -> !conflictColumns.contains(col))
                .map(col -> col + " = VALUES(" + col + ")")
                .collect(Collectors.joining(", "));

        return insert + " ON DUPLICATE KEY UPDATE " + updates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> placeholders, List<String> conflictColumns) {
        String insert = insert(table, columns, placeholders);
        String updates = columns.stream()
                .filter(col -> !conflictColumns.contains(col))
                .map(col -> col + " = VALUES(" + col + ")")
                .collect(Collectors.joining(", "));

        return insert + " ON DUPLICATE KEY UPDATE " + updates;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String autoIncrement() {
        return "AUTO_INCREMENT";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String quoteIdentifier(String identifier) {
        return "`" + identifier + "`";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(ColumnType type) {
        switch (type) {
            case NULL:
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

        if (column.isAutoIncrement()) {
            if (!(column.isPrimaryKey() || column.isUnique())) {
                throw new IllegalArgumentException("AUTO_INCREMENT requires PRIMARY KEY or UNIQUE constraint in MariaDB.");
            }
            sb.append(" ").append(autoIncrement());
        }

        if (column.isPrimaryKey()) sb.append(" PRIMARY KEY");
        if (column.isUnique()) sb.append(" UNIQUE");
        if (column.isNotNull()) sb.append(" NOT NULL");
        if (column.getDefaultValue() != null) {
            String defaultValue = column.getDefaultValue();
            boolean isSQLFunction = defaultValue.matches("(?i)^CURRENT_(DATE|TIME|TIMESTAMP)$");
            boolean isString = column.getType() == ColumnType.STRING;

            if (isString && !isSQLFunction && !(defaultValue.startsWith("'") && defaultValue.endsWith("'"))) {
                defaultValue = "'" + defaultValue + "'";
            }

            sb.append(" DEFAULT ").append(defaultValue);
        }
        if (column.getCheckConstraint() != null) sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");

        return sb.toString();
    }

    public String mapColumnType(SQLColumn column) {
        switch (column.getType()) {
            case UUID:
                return "CHAR(36)";
            case SHORT:
                return "SMALLINT";
            case INT:
                return "INT";
            case BOOLEAN:
                return "TINYINT(1)";
            case LONG:
                return "BIGINT";
            case DECIMAL:
                return "DECIMAL" + (column.getOptions().isEmpty() ? "(10,2)" : buildTypeOptions(column));
            case STRING:
                return "VARCHAR" + (column.getOptions().isEmpty() ? "(255)" : buildTypeOptions(column));
            case TEXT:
                return "TEXT";
            case ENUM:
                if (column.getOptions().isEmpty()) {
                    throw new IllegalArgumentException("ENUM type requires options to be specified.");
                }
                return "ENUM" + buildTypeOptions(column);
            case JSON:
            case JSONB:
                return "JSON";
            case BLOB:
                return "BLOB";
            case BYTE:
                return "BINARY(1)";
            case DOUBLE:
                return "DOUBLE";
            case FLOAT:
                return "FLOAT";
            case DATE:
                return "DATE";
            case TIME:
                return "TIME";
            case DATETIME:
                return "DATETIME" + (column.getOptions().isEmpty() ? "" : buildTypeOptions(column));
            case TIMESTAMP:
                return "TIMESTAMP" + (column.getOptions().isEmpty() ? "" : buildTypeOptions(column));
            default:
                throw new UnsupportedOperationException("Unsupported column type: " + column.getType());
        }
    }

}
