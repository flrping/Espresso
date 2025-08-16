package dev.flrp.espresso.storage.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a column in an SQL table.
 * This class allows you to define the name, type, and various constraints of the column.
 *
 * <p>If you are using a data type that takes in parameters (like VARCHAR(255)), you can specify those as options.
 * For example, to create a VARCHAR column with a length of 255, you can use
 * `new SQLColumn("name", ColumnType.VARCHAR, 255)`.</p>
 */

public class SQLColumn {

    private final String name;
    private final ColumnType type;
    private final List<Object> options;

    private boolean primaryKey = false;
    private boolean unique = false;
    private boolean notNull = false;
    private boolean autoIncrement = false;
    private String defaultValue = null;
    private String checkConstraint = null;

    public SQLColumn(String name, ColumnType type) {
        this.name = name;
        this.type = type;
        this.options = new ArrayList<>();
    }

    public SQLColumn(String name, ColumnType type, Object... options) {
        this.name = name;
        this.type = type;
        this.options = Arrays.asList(options);
    }

    /**
     * Marks this column as the primary key.
     * This will also set the column to NOT NULL.
     *
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn primaryKey() {
        this.primaryKey = true;
        this.notNull = true;
        return this;
    }

    /**
     * Marks this column as unique.
     * This will ensure that all values in this column are distinct.
     *
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn unique() {
        this.unique = true;
        return this;
    }

    /**
     * Marks this column as NOT NULL.
     * This will ensure that the column cannot have NULL values.
     *
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn notNull() {
        this.notNull = true;
        return this;
    }

    /**
     * Marks this column as auto-incrementing.
     * This is typically used for primary key columns.
     *
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn autoIncrement() {
        this.autoIncrement = true;
        return this;
    }

    /**
     * Sets a default value for this column.
     * This value will be used if no value is provided during insertion.
     *
     * @param defaultValue The default value to set for the column.
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Sets a check constraint for this column.
     * This constraint will be enforced by the database to ensure that all values in this column meet the specified condition.
     *
     * @param condition The SQL condition that must be satisfied for values in this column.
     * @return The current instance of SQLColumn for method chaining.
     */
    public SQLColumn check(String condition) {
        this.checkConstraint = condition;
        return this;
    }

    /**
     * Returns the name of the column.
     *
     * @return The name of the column.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the column.
     *
     * @return The ColumnType of the column.
     */
    public ColumnType getType() {
        return type;
    }

    /**
     * Returns the options specified for this column.
     * This can include parameters for types like VARCHAR, or other options.
     *
     * @return A list of options for the column.
     */
    public List<Object> getOptions() {
        return options;
    }

    /**
     * Checks if this column is marked as the primary key.
     *
     * @return true if this column is a primary key, false otherwise.
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * Checks if this column is marked as unique.
     *
     * @return true if this column is unique, false otherwise.
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Checks if this column is marked as NOT NULL.
     *
     * @return true if this column is NOT NULL, false otherwise.
     */
    public boolean isNotNull() {
        return notNull;
    }

    /**
     * Checks if this column is marked as auto-incrementing.
     *
     * @return true if this column is auto-increment, false otherwise.
     */
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    /**
     * Returns the default value for this column.
     * This value will be used if no value is provided during insertion.
     *
     * @return The default value for the column, or null if not set.
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Returns the check constraint for this column.
     * This constraint will be enforced by the database to ensure that all values in this column meet the specified condition.
     *
     * @return The check constraint for the column, or null if not set.
     */
    public String getCheckConstraint() {
        return checkConstraint;
    }

}
