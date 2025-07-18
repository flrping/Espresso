package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.SQLColumn;
import dev.flrp.espresso.storage.query.SQLType;

public class SQLiteStorageDialect implements SQLStorageDialect {

    @Override
    public String autoIncrement() {
        return "AUTOINCREMENT";
    }

    @Override
    public String quoteIdentifier(String identifier) {
        return "\"" + identifier + "\"";
    }

    @Override
    public String columnDefinition(SQLColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(quoteIdentifier(column.getName())).append(" ");

        switch (column.getType()) {
            case INT:
            case BOOLEAN:
            case LONG:
                sb.append("INTEGER");
                break;
            case STRING:
                sb.append("TEXT");
                break;
            case DOUBLE:
            case FLOAT:
                sb.append("REAL");
                break;
            case DATE:
                sb.append("DATE");
                break;
            case TIME:
                sb.append("TIME");
                break;
            case DATETIME:
                sb.append("DATETIME");
                break;
            case TIMESTAMP:
                sb.append("TIMESTAMP");
                break;
        }

        if (column.isPrimaryKey()) {
            sb.append(" PRIMARY KEY");
            if (column.isAutoIncrement()) {
                if (column.getType() == SQLType.INT || column.getType() == SQLType.LONG || column.getType() == SQLType.BOOLEAN) {
                    sb.append(" AUTOINCREMENT");
                } else {
                    throw new IllegalArgumentException("AUTOINCREMENT can only be applied to INTEGER PRIMARY KEY columns in SQLite");
                }
            }
        }

        if (column.isUnique()) sb.append(" UNIQUE");
        if (column.isNotNull()) sb.append(" NOT NULL");
        if (column.getDefaultValue() != null) {
            String defaultValue = column.getDefaultValue();
            boolean isString = column.getType() == SQLType.STRING;
            boolean isSQLFunction = defaultValue.matches("(?i)CURRENT_(DATE|TIME|TIMESTAMP)");
            if (isString && !isSQLFunction && !(defaultValue.startsWith("'") && defaultValue.endsWith("'"))) {
                defaultValue = "'" + defaultValue + "'";
            }
            sb.append(" DEFAULT ").append(defaultValue);
        }
        if (column.getCheckConstraint() != null) sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");

        return sb.toString();
    }


}

