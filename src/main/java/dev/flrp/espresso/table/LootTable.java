package dev.flrp.espresso.table;

import dev.flrp.espresso.condition.Condition;
import dev.flrp.espresso.condition.ConditionType;
import dev.flrp.espresso.condition.Conditionable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootTable implements Conditionable, Cloneable {

    private String identifier;
    private double weight;
    private final HashMap<String, Lootable> loots;

    private double entryTotalWeight;

    private List<Condition> conditions = new ArrayList<>();

    public LootTable(String name, double weight) {
        this.identifier = name;
        this.weight = weight;
        this.loots = new HashMap<>();
    }

    /**
     * @return The identifier of the loot group
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Set the name of the loot group.
     *
     * @param identifier The name to set.
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return The weight of the loot group.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Set the weight of the loot group.
     *
     * @param weight The weight to set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return all the loot in the group.
     */
    public HashMap<String, Lootable> getLoots() {
        return loots;
    }

    /**
     * Add a loot to the group.
     *
     * @param loot The loot to add.
     */
    public void addLoot(Lootable loot) {
        loots.put(loot.getIdentifier(), loot);
        entryTotalWeight += loot.getWeight();
    }

    /**
     * Get a loot from the group.
     *
     * @param identifier The identifier of the loot.
     * @return The loot.
     */
    public Lootable getLoot(String identifier) {
        return loots.get(identifier);
    }

    /**
     * Remove a loot from the group.
     *
     * @param identifier The identifier of the loot.
     */
    public void removeLoot(String identifier) {
        Lootable loot = loots.get(identifier);
        if (loot == null) return;
        loots.remove(identifier);
        entryTotalWeight -= loot.getWeight();
    }

    /**
     * Remove a loot from the group.
     *
     * @param loot The loot to remove.
     */
    public void removeLoot(Lootable loot) {
        loots.remove(loot.getIdentifier());
        entryTotalWeight -= loot.getWeight();
    }

    /**
     * Check if the group contains a loot.
     *
     * @param identifier The identifier of the loot.
     * @return If the group contains the loot.
     */
    public boolean containsLoot(String identifier) {
        return loots.containsKey(identifier);
    }

    /**
     * Check if the group contains a loot.
     *
     * @param loot The loot to check.
     * @return If the group contains the loot.
     */
    public boolean containsLoot(Lootable loot) {
        return loots.containsKey(loot.getIdentifier());
    }

    /**
     * @return The total weight of all entries in the group.
     */
    public double getEntryTotalWeight() {
        return entryTotalWeight;
    }

    /**
     * @return Roll a loot from the group.
     */
    public Lootable roll() {
        double roll;
        if (entryTotalWeight < 100) {
            roll = Math.random() * 100;
        } else {
            roll = Math.random() * entryTotalWeight;
        }

        for (Lootable loot : loots.values()) {
            if (roll <= loot.getWeight()) {
                return loot;
            } else {
                roll -= loot.getWeight();
            }
        }
        return null;
    }

    @Override
    public boolean hasCondition(ConditionType type) {
        return conditions.stream().anyMatch(condition -> condition.getType() == type);
    }

    @Override
    public boolean hasConditions() {
        return !conditions.isEmpty();
    }

    @Override
    public Condition getCondition(ConditionType type) {
        return conditions.stream().filter(condition -> condition.getType() == type).findFirst().orElse(null);
    }

    @Override
    public List<Condition> getConditions() {
        return conditions;
    }

    @Override
    public void setConditions(List<Condition> conditions) {
        this.conditions.clear();
        this.conditions.addAll(conditions);
    }

    @Override
    public void addCondition(Condition condition) {
        conditions.add(condition);
    }

    @Override
    public void removeCondition(Condition condition) {
        conditions.remove(condition);
    }

    @Override
    public LootTable clone() {
        try {
            LootTable clonedTable = (LootTable) super.clone();
            clonedTable.weight = this.weight;
            clonedTable.conditions = new ArrayList<>(this.conditions);
            return clonedTable;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }


}
