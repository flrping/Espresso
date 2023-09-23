package dev.flrp.espresso.hook.entity.custom;

import dev.flrp.espresso.hook.HookPurpose;
import dev.flrp.espresso.hook.entity.Levelled;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nullable;

public class MythicMobsEntityProvider implements EntityProvider, Levelled {

    private final MythicBukkit mythicMobs;

    public MythicMobsEntityProvider() {
        mythicMobs = isEnabled() ? (MythicBukkit) Bukkit.getPluginManager().getPlugin("MythicMobs") : null;
    }

    @Override
    public String getName() {
        return "MythicMobs";
    }

    @Override
    public HookPurpose getPurpose() {
        return EntityProvider.super.getPurpose();
    }

    @Override @Nullable
    public String getCustomEntityName(LivingEntity entity) {
        if(!isCustomEntity(entity)) return null;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getType().getInternalName();
    }

    @Override
    public boolean isCustomEntity(LivingEntity entity) {
        if(mythicMobs == null) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).isPresent();
    }

    @Override
    public boolean hasLevel(LivingEntity entity) {
        if(!isCustomEntity(entity)) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel() > 0;
    }

    @Override
    public double getLevel(LivingEntity entity) {
        if(!isCustomEntity(entity)) return 0;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel();
    }

}
