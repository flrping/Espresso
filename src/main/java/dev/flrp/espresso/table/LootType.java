package dev.flrp.espresso.table;

public enum LootType {

    NONE,
    ECONOMY,
    ITEM,
    CUSTOM_ITEM,
    POTION,
    COMMAND;

    public static LootType getByName(String type) {
        for(LootType lootType : LootType.values()) {
            if(lootType.name().equalsIgnoreCase(type)) return lootType;
        }
        return NONE;
    }

}
