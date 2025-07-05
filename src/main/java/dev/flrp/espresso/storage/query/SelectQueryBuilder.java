package dev.flrp.espresso.storage.query;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;
import dev.flrp.espresso.storage.provider.SQLStorageProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectQueryBuilder {

    private final String table;
    private final SQLStorageDialect dialect;
    private final SQLStorageBehavior behavior;

    private List<String> columns = new ArrayList<>();
    private final List<String> joins = new ArrayList<>();
    private String whereClause = null;
    private List<Object> whereParams = new ArrayList<>();
    private String orderByClause = null;
    private String limitClause = null;

    public SelectQueryBuilder(String table, SQLStorageProvider provider) {
        this.table = table;
        this.dialect = provider.getDialect();
        this.behavior = (SQLStorageBehavior) provider.getBehavior();
    }

    public SelectQueryBuilder columns(String... cols) {
        this.columns = new ArrayList<>(Arrays.asList(cols));
        return this;
    }

    public SelectQueryBuilder columns(SQLColumn... cols) {
        List<String> colNames = new ArrayList<>();
        for (SQLColumn col : cols) {
            colNames.add(col.getName());
        }
        this.columns = colNames;
        return this;
    }

    public SelectQueryBuilder where(String clause, Object... params) {
        this.whereClause = clause;
        this.whereParams = new ArrayList<>(Arrays.asList(params));
        return this;
    }

    public SelectQueryBuilder join(String joinClause) {
        joins.add("JOIN " + joinClause);
        return this;
    }

    public SelectQueryBuilder leftJoin(String joinClause) {
        joins.add("LEFT JOIN " + joinClause);
        return this;
    }

    public SelectQueryBuilder rightJoin(String joinClause) {
        joins.add("RIGHT JOIN " + joinClause);
        return this;
    }

    public SelectQueryBuilder orderBy(String orderByClause) {
        this.orderByClause = orderByClause;
        return this;
    }

    public SelectQueryBuilder limit(String limitClause) {
        this.limitClause = limitClause;
        return this;
    }

    public String build() {
        return dialect.select(table, columns, joins, whereClause, orderByClause, limitClause);
    }

    public List<Object> getParameters() {
        return whereParams;
    }

    public void execute() {
        String sql = build();
        behavior.query(sql, whereParams);
    }
}
