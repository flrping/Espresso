package dev.flrp.espresso.storage.query;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UpdateQueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private final List<String> columns = new ArrayList<>();
    private final List<Object> values = new ArrayList<>();
    private String whereClause;
    private List<Object> whereParams = Collections.emptyList();

    public UpdateQueryBuilder(String table, SQLStorageProvider provider) {
        this.table = table;
        this.dialect = provider.getDialect();
        this.behavior = (SQLStorageBehavior) provider.getBehavior();
    }

    public UpdateQueryBuilder set(String column, Object value) {
        columns.add(column);
        values.add(value);
        return this;
    }

    public UpdateQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    public void execute() throws ProviderException {
        String sql = dialect.update(table, columns, whereClause);
        List<Object> allParams = new ArrayList<>(values);
        allParams.addAll(whereParams);
        behavior.query(sql, allParams);
    }

}
