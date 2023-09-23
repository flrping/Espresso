package dev.flrp.espresso.table.loot;

import dev.flrp.espresso.hook.item.ItemProvider;
import dev.flrp.espresso.table.LootableCustomItem;
import org.bukkit.entity.Player;

public class LootCustomItem implements LootableCustomItem {

    private String identifier;
    private double weight;
    private double amount;
    private String customItemName;
    private ItemProvider itemProvider;

    public LootCustomItem(String identifier, double weight, double amount) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
    }

    public LootCustomItem(String identifier, double weight, double amount, String customItemName) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
        this.customItemName = customItemName;
    }

    public LootCustomItem(String identifier, double weight, double amount, String customItemName, ItemProvider itemProvider) {
        this.identifier = identifier;
        this.weight = weight;
        this.amount = amount;
        this.customItemName = customItemName;
        this.itemProvider = itemProvider;
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
    public void reward(Player player) {
        itemProvider.giveItem(player, customItemName);
    }

    @Override
    public String getCustomItemName() {
        return customItemName;
    }

    @Override
    public void setCustomItemName(String itemName) {
        this.customItemName = itemName;
    }

    @Override
    public ItemProvider getItemProvider() {
        return itemProvider;
    }

    @Override
    public void setItemProvider(ItemProvider itemProvider) {
        this.itemProvider = itemProvider;
    }

}
