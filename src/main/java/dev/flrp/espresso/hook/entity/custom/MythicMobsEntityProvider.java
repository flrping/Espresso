package dev.flrp.espresso.hook.entity.custom;

import dev.flrp.espresso.hook.HookPurpose;
import dev.flrp.espresso.hook.entity.Levelled;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import jakarta.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class MythicMobsEntityProvider implements EntityProvider, Levelled {

    private final MythicBukkit mythicMobs;
    private final List<String> entityNames = new ArrayList<>();

    public MythicMobsEntityProvider() {
        mythicMobs = isEnabled() ? (MythicBukkit) Bukkit.getPluginManager().getPlugin("MythicMobs") : null;
        if (mythicMobs != null) {
            for (MythicMob mob : mythicMobs.getMobManager().getMobTypes()) {
                try {
                    org.bukkit.entity.EntityType.valueOf(mob.getInternalName());
                } catch (IllegalArgumentException e) {
                    entityNames.add(mob.getInternalName());
                }
            }
        }
    }

    @Override
    public String getName() {
        return "MythicMobs";
    }

    @Override
    public EntityType getType() {
        return EntityType.MYTHIC_MOBS;
    }

    @Override
    public HookPurpose getPurpose() {
        return EntityProvider.super.getPurpose();
    }

    @Override
    @Nullable
    public String getCustomEntityName(LivingEntity entity) {
        if (!isCustomEntity(entity)) return null;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getType().getInternalName();
    }

    @Override
    public boolean isCustomEntity(LivingEntity entity) {
        if (mythicMobs == null) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).isPresent();
    }

    @Override
    public boolean isCustomEntity(String entity) {
        if (mythicMobs == null) return false;
        return mythicMobs.getMobManager().getMythicMob(entity).isPresent();
    }

    @Override
    public List<String> getCustomEntityNames() {
        if (mythicMobs == null) return null;
        return entityNames;
    }

    @Override
    public boolean hasLevel(LivingEntity entity) {
        if (!isCustomEntity(entity)) return false;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel() > 0;
    }

    @Override
    public double getLevel(LivingEntity entity) {
        if (!isCustomEntity(entity)) return 0;
        return mythicMobs.getMobManager().getActiveMob(entity.getUniqueId()).get().getLevel();
    }

}
