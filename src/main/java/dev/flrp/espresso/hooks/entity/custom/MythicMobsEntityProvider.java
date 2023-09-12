package dev.flrp.espresso.hooks.entity.custom;

import dev.flrp.espresso.hooks.entity.Levelled;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class MythicMobsEntityProvider implements EntityProvider, Levelled {

    private final MythicBukkit mythicMobs = Bukkit.getPluginManager().isPluginEnabled("MythicMobs") ?
            (MythicBukkit) Bukkit.getPluginManager().getPlugin("MythicMobs") : null;

    @Override
    public boolean isEnabled() {
        return mythicMobs != null;
    }

    @Override
    public String getCustomEntityName(LivingEntity entity) {
        if(mythicMobs == null) return null;
        if(mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).isPresent()) return null;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getType().getInternalName();
    }

    @Override
    public boolean isCustomEntity(LivingEntity entity) {
        if(mythicMobs == null) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).isPresent();
    }

    @Override
    public boolean hasLevel(LivingEntity entity) {
        if(mythicMobs == null) return false;
        if(mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).isPresent()) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel() > 0;
    }

    @Override
    public double getLevel(LivingEntity entity) {
        if(mythicMobs == null) return 0;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel();
    }



}
