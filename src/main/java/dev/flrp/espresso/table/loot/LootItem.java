package dev.flrp.espresso.table.loot;

import dev.flrp.espresso.table.LootableItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LootItem implements LootableItem {

    private String identifier;
    private ItemStack itemStack;
    private double weight;
    private double amount;

    public LootItem(String identifier, ItemStack itemStack, double weight) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
        this.amount = itemStack.getAmount();
    }

    public LootItem(String identifier, ItemStack itemStack, double weight, double amount) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
        this.amount = amount;
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
        itemStack.setAmount((int) Math.floor(amount));
    }

    @Override
    public void reward(Player player) {
        player.getInventory().addItem(itemStack);
    }

    @Override
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

}
