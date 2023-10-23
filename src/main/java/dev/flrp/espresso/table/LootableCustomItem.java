package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.item.ItemProvider;
import org.bukkit.entity.Player;

public class LootableCustomItem implements Lootable {

    private String identifier;
    private double weight;
    private double amount;
    private double addition = 0;
    private String customItemName;
    private ItemProvider itemProvider;

    private String message;

    public LootableCustomItem(String identifier, double weight, double amount, String customItemName) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
        this.customItemName = customItemName;
    }

    public LootableCustomItem(String identifier, double weight, double amount, String customItemName, ItemProvider itemProvider) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
        this.customItemName = customItemName;
        this.itemProvider = itemProvider;
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
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAddition() {
        return addition;
    }

    @Override
    public void setAddition(double addition) {
        this.addition = addition;
    }

    @Override
    public void reward(Player player) {
        itemProvider.giveItem(player, customItemName);
    }

    @Override
    public void reward(Player player, double amount) {
        itemProvider.giveItem(player, customItemName);
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

    public ItemProvider getItemProvider() {
        return itemProvider;
    }

    public void setItemProvider(ItemProvider itemProvider) {
        this.itemProvider = itemProvider;
    }

}
