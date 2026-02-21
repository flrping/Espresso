package dev.flrp.espresso.hook.spawner;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import jakarta.annotation.Nullable;

public class WildStackerSpawnerProvider implements SpawnerProvider {

    @Override
    public String getName() {
        return "WildStacker";
    }

    @Override
    public SpawnerType getType() {
        return SpawnerType.WILD_STACKER;
    }

    @Override
    public boolean isSpawner(Block block) {
        if (!isEnabled()) return false;
        return WildStackerAPI.getWildStacker().getSystemManager().isStackedSpawner(block);
    }

    @Override
    @Nullable
    public EntityType getSpawnerEntityType(Block block) {
        if (!isSpawner(block)) return null;
        return WildStackerAPI.getWildStacker().getSystemManager().getStackedSpawner(block.getLocation()).getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if (!isSpawner(block)) return 0;
        return WildStackerAPI.getWildStacker().getSystemManager().getStackedSpawner(block.getLocation()).getStackAmount();
    }

}
