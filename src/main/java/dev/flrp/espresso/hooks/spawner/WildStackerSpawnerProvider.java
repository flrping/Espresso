package dev.flrp.espresso.hooks.spawner;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public class WildStackerSpawnerProvider implements SpawnerProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("WildStacker");
    }

    @Override
    public boolean isSpawner(Block block) {
        return WildStackerAPI.getWildStacker().getSystemManager().isStackedSpawner(block);
    }

    @Override
    public EntityType getSpawnerType(Block block) {
        return WildStackerAPI.getWildStacker().getSystemManager().getStackedSpawner(block.getLocation()).getSpawnedType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        return WildStackerAPI.getWildStacker().getSystemManager().getStackedSpawner(block.getLocation()).getStackAmount();
    }

}
