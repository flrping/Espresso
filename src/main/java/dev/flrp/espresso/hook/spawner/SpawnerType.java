package dev.flrp.espresso.hook.spawner;

public enum SpawnerType {

    NONE,
    EPIC_SPAWNERS,
    ROSE_STACKER,
    ULTIMATE_STACKER,
    UPGRADEABLE_SPAWNERS,
    WILD_STACKER;

    public static SpawnerType getByName(String name) {
        for (SpawnerType spawnerType : values()) {
            if (spawnerType.name().equalsIgnoreCase(name)) {

                return spawnerType;
            }
        }
        return NONE;
    }

}
