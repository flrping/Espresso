package dev.flrp.espresso.hook.item;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ItemsAdderItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "ItemsAdder";
    }

    @Override
    public ItemType getType() {
        return ItemType.ITEMS_ADDER;
    }

    @Override @Nullable
    public String getCustomItemName(ItemStack item) {
        if(!isEnabled()) return null;
        CustomStack customItem = CustomStack.byItemStack(item);
        return customItem != null ? customItem.getId() : null;
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if(!isEnabled()) return false;
        CustomStack stack = CustomStack.byItemStack(item);
        return stack != null;
    }

    @Override
    public void giveItem(Player player, String itemName) {
        if(!isEnabled()) return;
        CustomStack stack = CustomStack.getInstance(itemName);
        if(stack != null) {
            player.getInventory().addItem(stack.getItemStack());
        }
    }

    @Override
    public void giveItem(Player player, String itemName, int amount) {
        if(!isEnabled()) return;
        CustomStack stack = CustomStack.getInstance(itemName);
        if(stack != null) {
            ItemStack item = stack.getItemStack();
            item.setAmount(amount);
            player.getInventory().addItem(item);
        }
    }

}
