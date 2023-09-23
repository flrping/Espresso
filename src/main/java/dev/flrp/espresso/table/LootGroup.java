package dev.flrp.espresso.table;

import java.util.HashMap;

public class LootGroup {

    private String name;
    private double weight;
    private final HashMap<String, Lootable> loots;

    public LootGroup(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.loots = new HashMap<>();
    }

    /**
     * @return The name of the loot group
     */
    String getName() {
        return name;
    }

    /**
     * Set the name of the loot group.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The weight of the loot group.
     */
    double getWeight() {
        return weight;
    }

    /**
     * Set the weight of the loot group.
     * @param weight The weight to set.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * @return all the loot in the group.
     */
    HashMap<String, Lootable> getLoots() {
        return loots;
    }

    /**
     * Add a loot to the group.
     * @param loot The loot to add.
     */
    public void addLoot(Lootable loot) {
        loots.put(loot.getIdentifier(), loot);
    }

    /**
     * Get a loot from the group.
     * @param identifier The identifier of the loot.
     * @return The loot.
     */
    public Lootable getLoot(String identifier) {
        return loots.get(identifier);
    }

    /**
     * Remove a loot from the group.
     * @param identifier The identifier of the loot.
     */
    public void removeLoot(String identifier) {
        loots.remove(identifier);
    }

    /**
     * Remove a loot from the group.
     * @param loot The loot to remove.
     */
    public void removeLoot(Lootable loot) {
        loots.remove(loot.getIdentifier());
    }

    /**
     * Check if the group contains a loot.
     * @param identifier The identifier of the loot.
     * @return If the group contains the loot.
     */
    public boolean containsLoot(String identifier) {
        return loots.containsKey(identifier);
    }

    /**
     * Check if the group contains a loot.
     * @param loot The loot to check.
     * @return If the group contains the loot.
     */
    public boolean containsLoot(Lootable loot) {
        return loots.containsKey(loot.getIdentifier());
    }

}
