package dev.flrp.espresso.storage.dialect;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import dev.flrp.espresso.storage.query.ColumnType;
import dev.flrp.espresso.storage.query.SQLColumn;

public class PostgreSQLStorageDialect extends SQLStorageDialect {

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> conflictColumns) {
        String insert = insert(table, columns);
        String conflict = String.join(", ", conflictColumns);

        String updates = columns.stream()
                .filter(col -> !conflictColumns.contains(col))
                .map(col -> col + " = EXCLUDED." + col)
                .collect(Collectors.joining(", "));
        String conflictAction = updates.isEmpty() ? "DO NOTHING" : "DO UPDATE SET " + updates;

        return insert + " ON CONFLICT (" + conflict + ") " + conflictAction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsert(String table, List<String> columns, List<String> placeholders, List<String> conflictColumns) {
        String insert = insert(table, columns, placeholders);
        String conflict = String.join(", ", conflictColumns);

        String updates = columns.stream()
                .filter(col -> !conflictColumns.contains(col))
                .map(col -> col + " = EXCLUDED." + col)
                .collect(Collectors.joining(", "));
        String conflictAction = updates.isEmpty() ? "DO NOTHING" : "DO UPDATE SET " + updates;

        return insert + " ON CONFLICT (" + conflict + ") " + conflictAction;
    }

    /**
     * Creates string to create enum type in the database.
     * This is postgres specific.
     * 
     * @param enumName   The name of the enum type.
     * @param enumValues The values of the enum type.
     * @return The SQL statement to create the enum type.
     */
    public String createEnumTypes(String enumName, List<String> enumValues) {
        if (enumName == null || enumName.isEmpty()) {
            throw new IllegalArgumentException("Enum name must not be empty");
        }
        if (enumValues == null || enumValues.isEmpty()) {
            throw new IllegalArgumentException("Enum values must not be empty");
        }

        return "DO $$ BEGIN " +
                "IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = '" + enumName + "') THEN " +
                "CREATE TYPE " + quoteIdentifier(enumName) + " AS ENUM (" +
                enumValues.stream()
                        .map(val -> "'" + val.replace("'", "''") + "'")
                        .collect(Collectors.joining(", "))
                +
                "); END IF; " +
                "END $$;";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String autoIncrement() {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(ColumnType type) {
        return Objects.requireNonNull(type) != ColumnType.NULL;
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
    public String columnDefinition(SQLColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(quoteIdentifier(column.getName())).append(" ");

        if (column.isAutoIncrement()) {
            sb.append("SERIAL");
            if (column.isPrimaryKey()) {
                sb.append(" PRIMARY KEY");
            }
        } else {
            sb.append(mapColumnType(column));

            if (column.isPrimaryKey())
                sb.append(" PRIMARY KEY");
            if (column.isUnique())
                sb.append(" UNIQUE");
            if (column.isNotNull())
                sb.append(" NOT NULL");

            if (column.getDefaultValue() != null) {
                String defaultValue = column.getDefaultValue();
                boolean isSQLFunction = defaultValue.matches("(?i)^CURRENT_(DATE|TIME|TIMESTAMP)$");
                boolean isString = column.getType() == ColumnType.STRING;

                if (isString && !isSQLFunction && !(defaultValue.startsWith("'") && defaultValue.endsWith("'"))) {
                    defaultValue = "'" + defaultValue + "'";
                }

                sb.append(" DEFAULT ").append(defaultValue);
            }

            if (column.getCheckConstraint() != null) {
                sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");
            }
        }

        return sb.toString();
    }

    public String mapColumnType(SQLColumn column) {
        switch (column.getType()) {
            case UUID:
                return "UUID";
            case SHORT:
            case BYTE:
                return "SMALLINT";
            case INT:
                return "INTEGER";
            case BOOLEAN:
                return "BOOLEAN";
            case LONG:
                return "BIGINT";
            case DECIMAL:
                return "DECIMAL" + (column.getOptions().isEmpty() ? "(10,2)" : buildTypeOptions(column));
            case STRING:
                return "VARCHAR" + (column.getOptions().isEmpty() ? "(255)" : buildTypeOptions(column));
            case TEXT:
                return "TEXT";
            case ENUM:
                if (column.getOptions().size() != 1) {
                    throw new IllegalArgumentException("ENUM type must have exactly one option for the enum name");
                }
                return column.getOptions().get(0).toString();
            case JSON:
            case JSONB:
                return "JSONB";
            case BLOB:
                return "BYTEA";
            case DOUBLE:
                return "DOUBLE PRECISION";
            case FLOAT:
                return "REAL";
            case DATE:
                return "DATE";
            case TIME:
                return "TIME";
            case DATETIME:
            case TIMESTAMP:
                return "TIMESTAMP";
            default:
                throw new UnsupportedOperationException("Unsupported column type: " + column.getType());
        }
    }

}
