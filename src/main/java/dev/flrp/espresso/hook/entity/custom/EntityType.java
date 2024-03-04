package dev.flrp.espresso.hook.entity.custom;

public enum EntityType {

    NONE,
    MYTHIC_MOBS,
    ITEMS_ADDER;

    public static EntityType getByName(String name) {
        for (EntityType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return NONE;
    }

}
