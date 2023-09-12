package dev.flrp.espresso.hooks.spawner;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public class RoseStackerSpawnerProvider implements SpawnerProvider {

    private final RoseStackerAPI roseStackerAPI = RoseStackerAPI.getInstance();

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("RoseStacker");
    }

    @Override
    public boolean isSpawner(Block block) {
        return roseStackerAPI.isSpawnerStacked(block);
    }

    @Override @Nullable
    public EntityType getSpawnerType(Block block) {
        if(!isSpawner(block)) return null;
        return roseStackerAPI.getStackedSpawner(block).getSpawner().getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if(!isSpawner(block)) return 0;
        return roseStackerAPI.getStackedSpawner(block).getStackSize();
    }

}
