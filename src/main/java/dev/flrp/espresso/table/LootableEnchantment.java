package dev.flrp.espresso.table;

import org.bukkit.enchantments.Enchantment;

public class LootableEnchantment implements Lootable {

    private String identifier;
    private Enchantment enchantment;
    private double weight;
    private double min;
    private double max;

    private String message;

    public LootableEnchantment(String identifier, Enchantment enchantment, double weight) {
        this.identifier = identifier;
        this.enchantment = enchantment;
        this.weight = weight;
    }

    public LootableEnchantment(String identifier, Enchantment enchantment, double weight, double min, double max) {
        this.identifier = identifier;
        this.enchantment = enchantment;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    @Override
    public LootType getType() {
        return LootType.ENCHANTMENT;
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

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public void setEnchantment(Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    @Override
    public LootableEnchantment clone() {
        try {
            return (LootableEnchantment) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
