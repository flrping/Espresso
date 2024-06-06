package dev.flrp.espresso.table;

import org.bukkit.potion.PotionEffectType;

public class LootablePotionEffect implements Lootable {

    private String identifier;
    private PotionEffectType effectType;
    private int amplifier;
    private double min;
    private double max;
    private double weight;

    private String message;

    public LootablePotionEffect(String identifier, PotionEffectType effectType, int amplifier, double weight) {
        this.identifier = identifier;
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.weight = weight;

    }

    public LootablePotionEffect(String identifier, PotionEffectType effectType, int amplifier, double weight, double min, double max) {
        this.identifier = identifier;
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.weight = weight;
        this.min = min;
        this.max = max;
    }

    @Override
    public LootType getType() {
        return LootType.POTION;
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

    public PotionEffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }


    @Override
    public LootablePotionEffect clone() {
        try {
            return (LootablePotionEffect) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
