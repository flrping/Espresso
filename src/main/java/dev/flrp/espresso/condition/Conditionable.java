package dev.flrp.espresso.condition;

import java.util.List;

public interface Conditionable {

    boolean hasCondition(ConditionType type);

    boolean hasConditions();

    Condition getCondition(ConditionType type);

    List<Condition> getConditions();

    void setConditions(List<Condition> conditions);

    void addCondition(Condition condition);

    void removeCondition(Condition condition);

}
