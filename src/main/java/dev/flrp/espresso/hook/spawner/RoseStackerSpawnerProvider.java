package dev.flrp.espresso.hook.spawner;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import jakarta.annotation.Nullable;

public class RoseStackerSpawnerProvider implements SpawnerProvider {

    private final RoseStackerAPI roseStackerAPI;

    public RoseStackerSpawnerProvider() {
        roseStackerAPI = isEnabled() ? RoseStackerAPI.getInstance() : null;
    }

    @Override
    public String getName() {
        return "RoseStacker";
    }

    @Override
    public SpawnerType getType() {
        return SpawnerType.ROSE_STACKER;
    }

    @Override
    public boolean isSpawner(Block block) {
        if (roseStackerAPI == null) return false;
        return roseStackerAPI.isSpawnerStacked(block);
    }

    @Override
    @Nullable
    public EntityType getSpawnerEntityType(Block block) {
        if (!isSpawner(block)) return null;
        return roseStackerAPI.getStackedSpawner(block).getSpawner().getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if (!isSpawner(block)) return 0;
        return roseStackerAPI.getStackedSpawner(block).getStackSize();
    }

}
