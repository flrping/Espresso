package dev.flrp.espresso.table;

public class LootResult {

    // A convenience class for storing the results of loot table rolls.
    // This class is responsible for storing sources.

    private LootTable lootTable;
    private Lootable lootable;
    private double amount;

    public LootTable getLootTable() {
        return lootTable;
    }

    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    public Lootable getLootable() {
        return lootable;
    }

    public void setLootable(Lootable lootable) {
        this.lootable = lootable;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
