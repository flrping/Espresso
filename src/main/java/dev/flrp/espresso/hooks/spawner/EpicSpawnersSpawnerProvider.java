package dev.flrp.espresso.hooks.spawner;

import com.craftaro.epicspawners.api.EpicSpawnersApi;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public class EpicSpawnersSpawnerProvider implements SpawnerProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("EpicSpawners");
    }

    @Override
    public boolean isSpawner(Block block) {
        return EpicSpawnersApi.getSpawnerManager().isSpawner(block.getLocation());
    }

    @Override @Nullable
    public EntityType getSpawnerType(Block block) {
        if(!isSpawner(block)) return null;
        return EpicSpawnersApi.getSpawnerManager().getSpawner(block.getLocation()).getCreatureSpawner().getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if(!isSpawner(block)) return 0;
        return EpicSpawnersApi.getSpawnerManager().getSpawner(block.getLocation()).getStackSize();
    }

}
