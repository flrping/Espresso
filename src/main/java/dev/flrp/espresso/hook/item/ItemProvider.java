package dev.flrp.espresso.hook.item;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemProvider extends Hook {

    ItemType getType();

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.ITEM;
    }

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


    /**
     * Gives the player a custom item.
     *
     * @param player The player to give the item to.
     * @param itemName The name of the custom item.
     */
    void giveItem(Player player, String itemName);

    /**
     * Gives the player a custom item.
     *
     * @param player The player to give the item to.
     * @param itemName The name of the custom item.
     * @param amount The amount of the custom item.
     */
    void giveItem(Player player, String itemName, int amount);

}
