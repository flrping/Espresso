package dev.flrp.espresso.hooks.entity;

import me.lokka30.levelledmobs.LevelledMobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class LevelledMobsHook implements Levelled {

    private static final LevelledMobs levelledMobs = Bukkit.getPluginManager().isPluginEnabled("LevelledMobs") ?
            (LevelledMobs) Bukkit.getPluginManager().getPlugin("LevelledMobs") : null;

    /**
     * Returns if the plugin is enabled.
     */
    public boolean isEnabled() {
        return levelledMobs != null;
    }

    /**
     * Returns if the entity is a custom entity.
     * This also checks if the entity is a levelled entity. Removing the need for a isLevelled() method.
     */
    public boolean isLevelledMob(LivingEntity entity) {
        if(levelledMobs == null) return false;
        return levelledMobs.levelManager.isLevelled(entity);
    }

    @Override
    public boolean hasLevel(LivingEntity entity) {
        if(levelledMobs == null) return false;
        return levelledMobs.levelManager.isLevelled(entity);
    }

    /**
     * Returns the level of the LevelledMob.
     */
    @Override
    public double getLevel(LivingEntity entity) {
        if(levelledMobs == null) return 1;
        return levelledMobs.levelManager.getLevelOfMob(entity);
    }

}
