package dev.flrp.espresso.hook.item;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class OraxenItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "Oraxen";
    }

    @Override
    public ItemType getType() {
        return ItemType.ORAXEN;
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

    @Override
    public void giveItem(Player player, String itemName) {
        if(!isEnabled()) return;
        ItemStack item = OraxenItems.getItemById(itemName).build();
        player.getInventory().addItem(item);
    }

    @Override
    public void giveItem(Player player, String itemName, int amount) {
        if(!isEnabled()) return;
        ItemStack item = OraxenItems.getItemById(itemName).build();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getItemStack(String itemName) {
        if(!isEnabled()) return null;
        return OraxenItems.getItemById(itemName).build();
    }

}
