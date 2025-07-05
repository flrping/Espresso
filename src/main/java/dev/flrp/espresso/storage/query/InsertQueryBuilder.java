package dev.flrp.espresso.storage.query;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;

import java.util.ArrayList;
import java.util.List;

public class InsertQueryBuilder {
    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;

    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();

    public InsertQueryBuilder(String table, SQLStorageProvider provider) {
        this.table = table;
        this.dialect = provider.getDialect();
        this.behavior = (SQLStorageBehavior) provider.getBehavior();
    }

    public InsertQueryBuilder column(String name, Object value) {
        columns.add(name);
        values.add(value);
        return this;
    }

    public InsertQueryBuilder column(SQLColumn column, Object value) {
        columns.add(column.getName());
        values.add(value);
        return this;
    }

    public String build() {
        return dialect.insert(table, columns);
    }

    public void execute() {
        String sql = build();
        behavior.query(sql, values);
    }

    public List<Object> getValues() {
        return values;
    }
}
