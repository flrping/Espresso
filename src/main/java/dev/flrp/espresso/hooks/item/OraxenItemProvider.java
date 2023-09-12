package dev.flrp.espresso.hooks.item;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class OraxenItemProvider implements ItemProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("Oraxen");
    }

    @Override
    public String getCustomItemName(ItemStack item) {
        return OraxenItems.getIdByItem(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        return OraxenItems.exists(item);
    }

}
