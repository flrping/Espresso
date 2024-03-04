package dev.flrp.espresso.util;

import dev.flrp.espresso.hook.economy.EconomyType;
import dev.flrp.espresso.hook.item.ItemType;
import dev.flrp.espresso.table.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.potion.PotionEffectType;

public class LootUtils {

    /**
     * This file is a utility class to easily create lootable objects. It allows API users to keep configurations
     * consistent and easy to implement.
     */

    public static LootableEconomy createEconomyLoot(ConfigurationSection section) {
        String identifier = section.getString("identifier");
        double weight = section.getDouble("weight");
        double min = section.getDouble("min");
        double max = section.getDouble("max");
        EconomyType type = EconomyType.getByName(section.getString("from"));
        return new LootableEconomy(identifier, type, weight, min, max);
    }

    public static LootableItem createItemLoot(ConfigurationSection section) {
        String identifier = section.getString("identifier");
        double weight = section.getDouble("weight");
        double min = section.getDouble("min");
        double max = section.getDouble("max");
        return new LootableItem(identifier, ItemUtils.createItem(section), weight, min, max);
    }

    public static LootableCommand createCommandLoot(ConfigurationSection section) {
        String identifier = section.getString("identifier");
        double weight = section.getDouble("weight");
        double min = section.getDouble("min");
        double max = section.getDouble("max");
        return new LootableCommand(identifier, section.getString("command"), weight, min, max);
    }

    public static LootableCustomItem createCustomItemLoot(ConfigurationSection section) {
        String identifier = section.getString("identifier");
        double weight = section.getDouble("weight");
        double min = section.getDouble("min");
        double max = section.getDouble("max");
        ItemType type = ItemType.getByName(section.getString("from"));
        return new LootableCustomItem(identifier, section.getString("item"), type, weight, min, max);
    }

    public static LootablePotionEffect createPotionEffectLoot(ConfigurationSection section) {
        String identifier = section.getString("identifier");
        double weight = section.getDouble("weight");
        double min = section.getDouble("min");
        double max = section.getDouble("max");
        PotionEffectType type = PotionEffectType.getByName(section.getString("effect"));
        int amplifier = section.getInt("amplifier") - 1;
        return new LootablePotionEffect(identifier, type, amplifier, weight, min, max);
    }

}
