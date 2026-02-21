package dev.flrp.espresso.hook.item;

import com.nexomc.nexo.api.NexoItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NexoItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "Nexo";
    }

    @Override
    public ItemType getType() {
        return ItemType.NEXO;
    }

    @Override
    public String getCustomItemName(ItemStack item) {
        if (!isCustomItem(item)) return null;
        return NexoItems.idFromItem(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if (!isEnabled()) return false;
        return NexoItems.exists(item);
    }

    @Override
    public void giveItem(Player player, String itemName) {
        if (!isEnabled()) return;
        ItemStack item = NexoItems.itemFromId(itemName).build();
        player.getInventory().addItem(item);
    }

    @Override
    public void giveItem(Player player, String itemName, int amount) {
        if (!isEnabled()) return;
        ItemStack item = NexoItems.itemFromId(itemName).build();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getItemStack(String itemName) {
        if (!isEnabled()) return null;
        return NexoItems.itemFromId(itemName).build();
    }

}
