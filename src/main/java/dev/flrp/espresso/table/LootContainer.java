package dev.flrp.espresso.table;

import jakarta.annotation.Nullable;
import java.util.HashMap;

public class LootContainer implements Cloneable {

    /**
     * The purpose of this class is to hold a set of loot tables for an object (like an entity).
     * The structure of loot classes is like this:
     * LootContainer (Contains all) -> LootTable (Contains Lootable) -> Lootable (Base)
     */

    private final HashMap<String, LootTable> lootTables = new HashMap<>();
    private double totalWeightOfTables = 0;

    /**
     * Add a loot table to the container.
     *
     * @param lootTable The loot table to add.
     */
    public void addLootTable(LootTable lootTable) {
        lootTables.put(lootTable.getIdentifier(), lootTable);
        totalWeightOfTables += lootTable.getWeight();
    }

    /**
     * Remove a loot table from the container.
     *
     * @param lootTable The loot table to remove.
     */
    public void removeLootTable(LootTable lootTable) {
        lootTables.remove(lootTable.getIdentifier());
        totalWeightOfTables -= lootTable.getWeight();
    }

    /**
     * Get a loot table from the container.
     *
     * @param name The name of the loot table to get.
     * @return The loot table.
     */
    public LootTable getLootTable(String name) {
        return lootTables.get(name);
    }

    /**
     * Get all the loot tables in the container.
     *
     * @return All the loot tables.
     */
    public HashMap<String, LootTable> getLootTables() {
        return lootTables;
    }

    /**
     * Get the total weight of all the loot tables in the container.
     *
     * @return The total weight of all the loot tables.
     */
    public double getTotalWeightOfTables() {
        return totalWeightOfTables;
    }

    /**
     * Roll a loot table from the container.
     *
     * @return The rolled loot table.
     *
     */
    @Nullable
    public LootTable rollLootTable() {
        double roll;
        if (totalWeightOfTables < 100) {
            roll = Math.random() * 100;
        } else {
            roll = Math.random() * totalWeightOfTables;
        }

        for (LootTable lootTable : lootTables.values()) {
            if (roll <= lootTable.getWeight()) {
                return lootTable;
            } else {
                roll -= lootTable.getWeight();
            }
        }
        return null;
    }

    /**
     * Roll a loot from a loot table in the container.
     *
     * @param lootTable The loot table to roll from.
     * @return The rolled loot.
     */
    @Nullable
    public Lootable rollLoot(LootTable lootTable) {
        return lootTable.roll();
    }

    /**
     * Roll a loot from a loot table in the container.
     *
     * @return The rolled loot. null if no loot table was rolled.
     */
    @Nullable
    public Lootable rollLoot() {
        LootTable lootTable = rollLootTable();
        if (lootTable == null) return null;
        return rollLoot(lootTable);
    }

    @Override
    public LootContainer clone() {
        try {
            LootContainer clonedContainer = (LootContainer) super.clone();
            clonedContainer.lootTables.clear();
            clonedContainer.totalWeightOfTables = 0;
            for (LootTable lootTable : lootTables.values()) {
                clonedContainer.addLootTable(lootTable.clone());
            }
            return clonedContainer;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
