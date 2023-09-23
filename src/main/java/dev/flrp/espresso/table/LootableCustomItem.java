package dev.flrp.espresso.table;

import dev.flrp.espresso.hook.item.ItemProvider;

public interface LootableCustomItem extends Lootable {

    String getCustomItemName();

    void setCustomItemName(String itemName);

    ItemProvider getItemProvider();

    void setItemProvider(ItemProvider itemProvider);

}
