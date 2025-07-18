package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.SQLColumn;
import dev.flrp.espresso.storage.query.SQLType;

public class PostgreSQLStorageDialect implements SQLStorageDialect {

    @Override
    public String autoIncrement() {
        return "";
    }

    @Override
    public String quoteIdentifier(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public String columnDefinition(SQLColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(quoteIdentifier(column.getName())).append(" ");

        if (column.isAutoIncrement() && column.isPrimaryKey()) {
            sb.append("SERIAL");
        } else {
            switch (column.getType()) {
                case INT:
                case BOOLEAN:
                    sb.append("INTEGER");
                    break;
                case LONG:
                    sb.append("BIGINT");
                    break;
                case STRING:
                    sb.append("VARCHAR(255)");
                    break;
                case DOUBLE:
                case FLOAT:
                    sb.append("DOUBLE PRECISION");
                    break;
                case DATE:
                    sb.append("DATE");
                    break;
                case TIME:
                    sb.append("TIME");
                    break;
                case DATETIME:
                case TIMESTAMP:
                    sb.append("TIMESTAMP");
                    break;
            }
        }

        if (!(column.isAutoIncrement() && column.isPrimaryKey())) {
            if (column.isPrimaryKey()) sb.append(" PRIMARY KEY");
            if (column.isUnique()) sb.append(" UNIQUE");
            if (column.isNotNull()) sb.append(" NOT NULL");
            if (column.getDefaultValue() != null) {
                String defaultValue = column.getDefaultValue();
                boolean isSQLFunction = defaultValue.matches("(?i)^CURRENT_(DATE|TIME|TIMESTAMP)$");
                boolean isString = column.getType() == SQLType.STRING;

                if (isString && !isSQLFunction && !(defaultValue.startsWith("'") && defaultValue.endsWith("'"))) {
                    defaultValue = "'" + defaultValue + "'";
                }

                sb.append(" DEFAULT ").append(defaultValue);
            }
            if (column.getCheckConstraint() != null) sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");
        }

        return sb.toString();
    }
}
