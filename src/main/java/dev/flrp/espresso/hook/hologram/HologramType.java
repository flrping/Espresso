package dev.flrp.espresso.hook.hologram;

public enum HologramType {

    NONE,
    DECENT_HOLOGRAMS;

    public static HologramType getByName(String name) {
        for (HologramType hologramType : values()) {
            if (hologramType.name().equalsIgnoreCase(name)) {
                return hologramType;
            }
        }
        return NONE;
    }

}
