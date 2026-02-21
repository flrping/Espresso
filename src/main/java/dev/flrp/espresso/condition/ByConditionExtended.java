package dev.flrp.espresso.condition;

import dev.flrp.espresso.hook.entity.custom.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ByConditionExtended implements Condition {

    private HashMap<EntityType, List<String>> entities = new HashMap<>();

    /**
     * Get the condition type.
     *
     * @return The condition type.
     */
    @Override
    public ConditionType getType() {
        return ConditionType.BY;
    }

    /**
     * Check if the entity type has the entity.
     *
     * @param entityType The entity type, or the hook the entity is from. (e.g. ITEMS_ADDER). NONE is for default entities.
     * @param entity     The entity to check.
     * @return If the entity is present.
     */
    public boolean check(EntityType entityType, String entity) {
        if (!entities.containsKey(entityType)) return false;
        return entities.get(entityType).contains(entity);
    }

    /**
     * Get the entities.
     *
     * @return The entities.
     */
    public HashMap<EntityType, List<String>> getEntities() {
        return entities;
    }

    /**
     * Set the entities.
     *
     * @param entities The entities.
     */
    public void setEntities(HashMap<EntityType, List<String>> entities) {
        this.entities = entities;
    }

    /**
     * Add an entity to the entity type.
     *
     * @param entityType The entity type, or the hook the entity is from. (e.g. ITEMS_ADDER). NONE is for default entities.
     * @param entity     The entity to add.
     */
    public void addEntity(EntityType entityType, String entity) {
        if (!entities.containsKey(entityType)) entities.put(entityType, new ArrayList<>());
        entities.get(entityType).add(entity);
    }

    /**
     * Remove an entity from the entity type.
     *
     * @param entityType The entity type, or the hook the entity is from. (e.g. ITEMS_ADDER). NONE is for default entities.
     * @param entity     The entity to remove.
     */
    public void removeEntity(EntityType entityType, String entity) {
        if (!entities.containsKey(entityType) || !entities.get(entityType).contains(entity)) return;
        entities.get(entityType).remove(entity);
    }

}
