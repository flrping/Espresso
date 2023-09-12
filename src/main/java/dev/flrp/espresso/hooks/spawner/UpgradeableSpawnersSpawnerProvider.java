package dev.flrp.espresso.hooks.spawner;

import me.angeschossen.upgradeablespawners.api.UpgradeableSpawnersAPI;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public class UpgradeableSpawnersSpawnerProvider implements SpawnerProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("EpicSpawners");
    }

    @Override
    public boolean isSpawner(Block block) {
        return UpgradeableSpawnersAPI.getInstance().getSpawner(block) != null;
    }

    @Override @Nullable
    public EntityType getSpawnerType(Block block) {
        if(!isSpawner(block)) return null;
        return UpgradeableSpawnersAPI.getInstance().getSpawner(block).getEntityType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        return 1;
    }

}
