package dev.flrp.espresso.hooks.item;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class MMOItemsItemProvider implements ItemProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("MMOItems");
    }

    @Override
    public String getCustomItemName(ItemStack item) {
        return MMOItems.getTypeName(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        return MMOItems.getType(item) != null;
    }

}
