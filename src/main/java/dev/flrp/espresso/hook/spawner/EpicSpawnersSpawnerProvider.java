package dev.flrp.espresso.hook.spawner;

import com.songoda.epicspawners.api.EpicSpawnersApi;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import jakarta.annotation.Nullable;

public class EpicSpawnersSpawnerProvider implements SpawnerProvider {

    @Override
    public String getName() {
        return "EpicSpawners";
    }

    @Override
    public SpawnerType getType() {
        return SpawnerType.EPIC_SPAWNERS;
    }

    @Override
    public boolean isSpawner(Block block) {
        if (!isEnabled()) return false;
        return EpicSpawnersApi.getSpawnerManager().isSpawner(block.getLocation());
    }

    @Override
    @Nullable
    public EntityType getSpawnerEntityType(Block block) {
        if (!isSpawner(block)) return null;
        return EpicSpawnersApi.getSpawnerManager().getSpawner(block.getLocation()).getCreatureSpawner().getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if (!isSpawner(block)) return 0;
        return EpicSpawnersApi.getSpawnerManager().getSpawner(block.getLocation()).getStackSize();
    }

}
