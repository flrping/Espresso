package dev.flrp.espresso.table;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LootablePotionEffect implements Lootable {

    private String identifier;
    private PotionEffectType effectType;
    private int amplifier;
    private int duration;
    private double weight;

    private String message;

    public LootablePotionEffect(String identifier, PotionEffectType effectType, int amplifier, int duration, double weight) {
        this.identifier = identifier;
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.duration = duration;
        this.weight = weight;
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
    public double getAmount() {
        return 1;
    }

    @Override
    public void setAmount(double amount) {
        // Do nothing.
    }

    @Override
    public double getAddition() {
        return 0;
    }

    @Override
    public void setAddition(double addition) {
        // Do nothing.
    }

    @Override
    public void reward(Player player) {
        player.addPotionEffect(new PotionEffect(effectType, duration, amplifier));
    }

    @Override
    public void reward(Player player, double amount) {
        player.addPotionEffect(new PotionEffect(effectType, duration, amplifier));
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
