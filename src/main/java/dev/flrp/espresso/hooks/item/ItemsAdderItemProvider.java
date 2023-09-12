package dev.flrp.espresso.hooks.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class ItemsAdderItemProvider implements ItemProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("ItemsAdder");
    }

    @Override
    public String getCustomItemName(ItemStack item) {
        CustomStack customEntity = CustomStack.byItemStack(item);
        return customEntity != null ? customEntity.getId() : null;
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        CustomStack stack = CustomStack.byItemStack(item);
        return stack != null;
    }
}
