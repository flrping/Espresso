package dev.flrp.espresso.table;

import org.bukkit.inventory.ItemStack;

public class LootableItem implements Lootable {

    private String identifier;
    private double weight;
    private double min;
    private double max;
    private ItemStack itemStack;

    private String message;

    public LootableItem(String identifier, ItemStack itemStack, double weight) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
    }

    public LootableItem(String identifier, ItemStack itemStack, double weight, double min, double max) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    @Override
    public LootType getType() {
        return LootType.ITEM;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getMin() {
        return min;
    }

    @Override
    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public double getMax() {
        return max;
    }

    @Override
    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public boolean hasMessage() {
        return message != null;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    @Override
    public LootableItem clone() {
        try {
            return (LootableItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
