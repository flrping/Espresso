package dev.flrp.espresso.hook.item;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jakarta.annotation.Nullable;

public class MMOItemsItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "MMOItems";
    }

    @Override
    public ItemType getType() {
        return ItemType.MMO_ITEMS;
    }

    @Override
    @Nullable
    public String getCustomItemName(ItemStack item) {
        if (!isCustomItem(item)) return null;
        return MMOItems.getTypeName(item);
    }

    @Override
    public boolean isCustomItem(ItemStack item) {
        if (!isEnabled()) return false;
        return MMOItems.getType(item) != null;
    }

    @Override
    public void giveItem(Player player, String itemDetails) {
        if (!isEnabled()) return;
        if (itemDetails == null || !itemDetails.contains(":")) return;
        String[] details = itemDetails.split(":");
        if (details.length < 2) return;
        String itemType = details[0];
        String itemName = details[1];
        MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(itemType), itemName);
        if (mmoitem == null) return;
        ItemStack item = mmoitem.newBuilder().build();
        player.getInventory().addItem(item);
    }

    @Override
    public void giveItem(Player player, String itemDetails, int amount) {
        if (!isEnabled()) return;
        if (itemDetails == null || !itemDetails.contains(":")) return;
        String[] details = itemDetails.split(":");
        if (details.length < 2) return;
        String itemType = details[0];
        String itemName = details[1];
        MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(itemType), itemName);
        if (mmoitem == null) return;
        ItemStack item = mmoitem.newBuilder().build();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getItemStack(String itemName) {
        if (!isEnabled()) return null;
        if (itemName == null || !itemName.contains(":")) return null;
        String[] details = itemName.split(":");
        if (details.length < 2) return null;
        String itemType = details[0];
        String itemName2 = details[1];
        MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(itemType), itemName2);
        return mmoitem != null ? mmoitem.newBuilder().build() : null;
    }

}
