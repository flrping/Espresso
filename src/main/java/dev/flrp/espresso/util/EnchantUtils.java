package dev.flrp.espresso.util;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

public class EnchantUtils {

    public static Enchantment getEnchantByVersion(String string) {
        if (Bukkit.getVersion().matches(".*(1\\.8|1\\.9|1\\.10|1\\.11|1\\.12).*")) {
            return Enchantment.getByName(string);
        } else {
            String sanitized = string.toLowerCase().replace(" ", "_");
            return Enchantment.getByKey(NamespacedKey.minecraft(sanitized));
        }
    }

    public static String convertEnchantToModern(String enchant) {
        String enchantment = enchant.toUpperCase();
        switch (enchantment) {
            case "ARROW_DAMAGE":
                return "POWER";
            case "ARROW_FIRE":
                return "FLAME";
            case "ARROW_INFINITE":
                return "INFINITY";
            case "ARROW_KNOCKBACK":
                return "PUNCH";
            case "DAMAGE_ALL":
                return "SHARPNESS";
            case "DAMAGE_ARTHROPODS":
                return "BANE OF ARTHROPODS";
            case "DAMAGE_UNDEAD":
                return "SMITE";
            case "DIG_SPEED":
                return "EFFICIENCY";
            case "DURABILITY":
                return "UNBREAKING";
            case "LOOT_BONUS_BLOCKS":
                return "FORTUNE";
            case "LOOT_BONUS_MOBS":
                return "LOOTING";
            case "OXYGEN":
                return "RESPIRATION";
            case "PROTECTION_ENVIRONMENTAL":
                return "PROTECTION";
            case "PROTECTION_EXPLOSIONS":
                return "BLAST PROTECTION";
            case "PROTECTION_FALL":
                return "FEATHER FALLING";
            case "PROTECTION_FIRE":
                return "FIRE PROTECTION";
            case "PROTECTION_PROJECTILE":
                return "PROJECTILE PROTECTION";
            case "WATER_WORKER":
                return "AQUA AFFINITY";
            case "BINDING_CURSE":
                return "CURSE OF BINDING";
            case "VANISHING_CURSE":
                return "CURSE OF VANISHING";
            default:
                return enchantment;
        }
    }

}
