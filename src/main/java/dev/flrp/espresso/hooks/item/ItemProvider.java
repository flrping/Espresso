package dev.flrp.espresso.hooks.item;

import dev.flrp.espresso.hooks.Hook;
import org.bukkit.inventory.ItemStack;

public interface ItemProvider extends Hook {

    /**
     * Gets the name of the custom item.
     *
     * @return The name of the custom item, usually its personal identifier from the plugin.
     */
    String getCustomItemName(ItemStack item);

    /**
     * Checks if the item is a custom item.
     *
     * @return Whether the item is a custom item.
     */
    boolean isCustomItem(ItemStack item);

}
