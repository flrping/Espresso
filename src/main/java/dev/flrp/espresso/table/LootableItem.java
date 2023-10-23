package dev.flrp.espresso.table;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LootableItem implements Lootable {

    private String identifier;
    private double weight;
    private double amount;
    private double addition = 0;
    private ItemStack itemStack;

    private String message;

    public LootableItem(String identifier, ItemStack itemStack, double weight) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
        this.amount = itemStack.getAmount();
    }

    public LootableItem(String identifier, ItemStack itemStack, double weight, double amount) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.weight = weight;
        this.amount = amount;
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
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
        itemStack.setAmount((int) Math.floor(amount));
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
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void reward(Player player, double amount) {
        itemStack.setAmount((int) Math.floor(amount));
        player.getInventory().addItem(itemStack);
        itemStack.setAmount((int) Math.floor(this.amount));
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

}
