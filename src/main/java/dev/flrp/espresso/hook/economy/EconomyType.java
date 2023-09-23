package dev.flrp.espresso.hook.economy;

public enum EconomyType {

    NONE,
    VAULT,
    PLAYER_POINTS,
    TOKEN_MANAGER;

    public static EconomyType getByName(String name) {
        for (EconomyType economyType : values()) {
            if (economyType.name().equalsIgnoreCase(name)) {
                return economyType;
            }
        }
        return NONE;
    }

}
