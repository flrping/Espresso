package dev.flrp.espresso.table;

import org.bukkit.inventory.ItemStack;

public interface LootableItem extends Lootable {

    ItemStack getItemStack();

    void setItemStack(ItemStack itemStack);

}

