package dev.flrp.espresso.storage.query;

public class SQLColumn {

    private final String name;
    private final SQLType type;

    private boolean primaryKey = false;
    private boolean unique = false;
    private boolean notNull = false;
    private boolean autoIncrement = false;
    private String defaultValue = null;
    private String checkConstraint = null;

    public SQLColumn(String name, SQLType type) {
        this.name = name;
        this.type = type;
    }

    public SQLColumn primaryKey() {
        this.primaryKey = true;
        this.notNull = true;
        return this;
    }

    public SQLColumn unique() {
        this.unique = true;
        return this;
    }

    public SQLColumn notNull() {
        this.notNull = true;
        return this;
    }

    public SQLColumn autoIncrement() {
        this.autoIncrement = true;
        return this;
    }

    public SQLColumn defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public SQLColumn check(String condition) {
        this.checkConstraint = condition;
        return this;
    }

    public String getName() {
        return name;
    }

    public SQLType getType() {
        return type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public boolean isUnique() {
        return unique;
    }
    
    public boolean isNotNull() {
        return notNull;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getCheckConstraint() {
        return checkConstraint;
    }

}
