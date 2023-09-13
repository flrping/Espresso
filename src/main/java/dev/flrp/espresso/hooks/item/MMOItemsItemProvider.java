package dev.flrp.espresso.hooks.item;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class MMOItemsItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "MMOItems";
    }

    @Override @Nullable
    public String getCustomItemName(ItemStack item) {
        if(!isCustomItem(item)) return null;
        return MMOItems.getTypeName(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if(!isEnabled()) return false;
        return MMOItems.getType(item) != null;
    }

}
