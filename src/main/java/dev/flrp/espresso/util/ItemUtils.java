package dev.flrp.espresso.util;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    public static ItemStack createItem(ConfigurationSection section) {
        Material material = Material.valueOf(section.getString("material"));
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            if(section.contains("name")) itemMeta.setDisplayName(StringUtils.parseColor(section.getString("name")));
            if(section.contains("lore")) itemMeta.setLore(StringUtils.parseColor(section.getStringList("lore")));
            if(section.contains("model-data")) itemMeta.setCustomModelData(section.getInt("model-data"));
            if(section.contains("enchantments")) {
                for(String enchantment : section.getStringList("enchantments")) {
                    String[] enchantmentSplit = enchantment.split(":");
                    itemMeta.addEnchant(EnchantUtils.getEnchantByVersion(enchantmentSplit[0]), Integer.parseInt(enchantmentSplit[1]), true);
                }
            }
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

}
