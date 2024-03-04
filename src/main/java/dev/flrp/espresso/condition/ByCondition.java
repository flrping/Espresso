package dev.flrp.espresso.condition;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class ByCondition implements Condition {

    private List<EntityType> entities = new ArrayList<>();

    @Override
    public ConditionType getType() {
        return ConditionType.BY;
    }

    public boolean check(EntityType entityType) {
        return entities.contains(entityType);
    }

    public List<EntityType> getEntities() {
        return entities;
    }

    public void setEntities(List<EntityType> entities) {
        this.entities = entities;
    }

    public void addEntity(EntityType entityType) {
        entities.add(entityType);
    }

    public void removeEntity(EntityType entityType) {
        entities.remove(entityType);
    }

}
