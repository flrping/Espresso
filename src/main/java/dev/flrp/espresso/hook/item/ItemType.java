package dev.flrp.espresso.hook.item;

public enum ItemType {

    NONE,
    ITEMS_ADDER,
    ORAXEN,
    MMO_ITEMS,
    NEXO;

    public static ItemType getByName(String name) {
        for (ItemType itemType : values()) {
            if (itemType.name().equalsIgnoreCase(name)) {
                return itemType;
            }
        }
        return NONE;
    }

}
