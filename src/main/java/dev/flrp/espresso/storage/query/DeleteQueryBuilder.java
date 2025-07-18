package dev.flrp.espresso.storage.query;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeleteQueryBuilder {
    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;
    private String whereClause;
    private List<Object> whereParams = Collections.emptyList();

    public DeleteQueryBuilder(String table, SQLStorageProvider provider) {
        this.table = table;
        this.dialect = provider.getDialect();
        this.behavior = (SQLStorageBehavior) provider.getBehavior();
    }

    public DeleteQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    public String build() {
        return dialect.delete(table, whereClause);
    }

    public void execute() throws ProviderException {
        String sql = dialect.delete(table, whereClause);
        behavior.query(sql, whereParams);
    }

}
