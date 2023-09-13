package dev.flrp.espresso.hooks.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ItemsAdderItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "ItemsAdder";
    }

    @Override @Nullable
    public String getCustomItemName(ItemStack item) {
        if(!isEnabled()) return null;
        CustomStack customEntity = CustomStack.byItemStack(item);
        return customEntity != null ? customEntity.getId() : null;
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if(!isEnabled()) return false;
        CustomStack stack = CustomStack.byItemStack(item);
        return stack != null;
    }

}
