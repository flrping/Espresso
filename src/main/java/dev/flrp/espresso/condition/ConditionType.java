package dev.flrp.espresso.condition;

public enum ConditionType {

    BY,
    WITH,
    BIOME,
    AGE,
    STAGE,
    WORLD,
    PERMISSION;

    public static ConditionType getByName(String name) {
        for (ConditionType type : ConditionType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}
