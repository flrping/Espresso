package dev.flrp.espresso.storage.dialect;

import dev.flrp.espresso.storage.query.SQLColumn;

public class MariaDBStorageDialect implements SQLStorageDialect {

    @Override
    public String autoIncrement() {
        return "AUTO_INCREMENT";
    }

    @Override
    public String quoteIdentifier(String identifier) {
        return "`" + identifier + "`";
    }

    @Override
    public String columnDefinition(SQLColumn column) {
        StringBuilder sb = new StringBuilder();
        sb.append(quoteIdentifier(column.getName())).append(" ");

        switch (column.getType()) {
            case INT:
            case BOOLEAN:
            case LONG:
                sb.append("INT");
                break;
            case STRING:
                sb.append("VARCHAR(255)");
                break;
            case DOUBLE:
            case FLOAT:
                sb.append("DOUBLE");
                break;
            case DATE:
                sb.append("DATE");
                break;
            case TIME:
                sb.append("TIME");
                break;
            case DATETIME:
            case TIMESTAMP:
                sb.append("DATETIME");
                break;
        }

        if (column.isAutoIncrement()) sb.append(" ").append(autoIncrement());
        if (column.isPrimaryKey()) sb.append(" PRIMARY KEY");
        if (column.isUnique()) sb.append(" UNIQUE");
        if (column.isNotNull()) sb.append(" NOT NULL");
        if (column.getDefaultValue() != null) sb.append(" DEFAULT ").append(column.getDefaultValue());
        if (column.getCheckConstraint() != null) sb.append(" CHECK (").append(column.getCheckConstraint()).append(")");
        return sb.toString();
    }
}
