package dev.flrp.espresso.table.loot;

import dev.flrp.espresso.table.LootablePotionEffect;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class LootPotionEffect implements LootablePotionEffect {

    private String identifier;
    private PotionEffectType effectType;
    private int amplifier;
    private int duration;
    private double weight;

    public LootPotionEffect(String identifier, PotionEffectType effectType, int amplifier, int duration, double weight) {
        this.identifier = identifier;
        this.effectType = effectType;
        this.amplifier = amplifier;
        this.duration = duration;
        this.weight = weight;
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
        // Do nothing
    }

    @Override
    public void reward(Player player) {
        player.addPotionEffect(effectType.createEffect(duration, amplifier));
    }

    @Override
    public PotionEffectType getEffectType() {
        return effectType;
    }

    @Override
    public void setEffectType(PotionEffectType effectType) {
        this.effectType = effectType;
    }

    @Override
    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

}
