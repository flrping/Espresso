package dev.flrp.espresso.hook.item;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class MMOItemsItemProvider implements ItemProvider {

    @Override
    public String getName() {
        return "MMOItems";
    }

    @Override
    public ItemType getType() {
        return ItemType.MMO_ITEMS;
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

    @Override
    public void giveItem(Player player, String itemDetails) {
        if(!isEnabled()) return;
        // itemDetails will be in the form of type:name
        String[] details = itemDetails.split(":");
        String itemType = details[0];
        String itemName = details[1];
        MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(itemType), itemName);
        ItemStack item = mmoitem.newBuilder().build();
        player.getInventory().addItem(item);
    }

    @Override
    public void giveItem(Player player, String itemDetails, int amount) {
        if(!isEnabled()) return;
        // itemDetails will be in the form of type:name
        String[] details = itemDetails.split(":");
        String itemType = details[0];
        String itemName = details[1];
        MMOItem mmoitem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(itemType), itemName);
        ItemStack item = mmoitem.newBuilder().build();
        item.setAmount(amount);
        player.getInventory().addItem(item);
    }

}
