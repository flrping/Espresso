package dev.flrp.espresso.hook.entity;

import io.github.arcaneplugins.levelledmobs.LevelledMobs;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class LevelledMobsHook implements Levelled {

    final LevelledMobs levelledMobs;

    public LevelledMobsHook() {
        levelledMobs = isEnabled() ? (LevelledMobs) Bukkit.getPluginManager().getPlugin("LevelledMobs") : null;
    }

    @Override
    public String getName() {
        return "LevelledMobs";
    }

    @Override
    public boolean hasLevel(LivingEntity entity) {
        if (levelledMobs == null) return false;
        return levelledMobs.getLevelManager().isLevelled(entity);
    }

    @Override
    public double getLevel(LivingEntity entity) {
        if (!hasLevel(entity)) return 1;
        return levelledMobs.getLevelManager().getLevelOfMob(entity);
    }

}
