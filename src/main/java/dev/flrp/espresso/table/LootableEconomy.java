package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.economy.EconomyType;

public class LootableEconomy implements Lootable, Cloneable {

    private String identifier;
    private double weight;
    private double min;
    private double max;
    private EconomyType economyType;

    private String message;

    public LootableEconomy(String identifier, EconomyType economyType, double weight, double min, double max) {
        this.identifier = identifier;
        this.economyType = economyType;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    @Override
    public LootType getType() {
        return LootType.ECONOMY;
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

    /**
     * @return The economy provider.
     */
    public EconomyType getEconomyType() {
        return economyType;
    }

    /**
     * Set the economy provider.
     * @param economyType The economy type.
     */
    public void setEconomyType(EconomyType economyType) {
        this.economyType = economyType;
    }

    @Override
    public LootableEconomy clone() {
        try {
            return (LootableEconomy) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
