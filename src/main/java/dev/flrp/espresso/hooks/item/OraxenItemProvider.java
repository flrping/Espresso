package dev.flrp.espresso.hooks.item;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class OraxenItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "Oraxen";
    }

    @Override @Nullable
    public String getCustomItemName(ItemStack item) {
        if(!isCustomItem(item)) return null;
        return OraxenItems.getIdByItem(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if(!isEnabled()) return false;
        return OraxenItems.exists(item);
    }

}
