package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.item.ItemType;

public class LootableCustomItem implements Lootable {

    private String identifier;
    private double weight;
    private double min;
    private double max;
    private String customItemName;
    private ItemType itemType;

    private String message;

    public LootableCustomItem(String identifier, String customItemName, ItemType itemType, double weight, double min, double max) {
        this.identifier = identifier;
        this.weight = weight;
        this.min = min;
        this.max = max;
        this.customItemName = customItemName;
        this.itemType = itemType;
    }

    @Override
    public LootType getType() {
        return LootType.CUSTOM_ITEM;
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

    public String getCustomItemName() {
        return customItemName;
    }

    public void setCustomItemName(String itemName) {
        this.customItemName = itemName;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public LootableCustomItem clone() {
        try {
            return (LootableCustomItem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
