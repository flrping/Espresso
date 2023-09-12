package dev.flrp.espresso.hooks.item;

import org.bukkit.inventory.ItemStack;

public interface ItemProvider {

    /**
     * @return Whether the plugin is enabled
     */
    boolean isEnabled();

    /**
     * @return The custom item name
     */
    String getCustomItemName(ItemStack item);

    /**
     * @return Whether the item is a custom item
     */
    boolean isCustomItem(ItemStack item);

}
