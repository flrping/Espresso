package dev.flrp.espresso.table;

import org.bukkit.potion.PotionEffectType;

public interface LootablePotionEffect extends Lootable {

    PotionEffectType getEffectType();

    void setEffectType(PotionEffectType effectType);

    int getAmplifier();

    void setAmplifier(int amplifier);

    int getDuration();

    void setDuration(int duration);

}
