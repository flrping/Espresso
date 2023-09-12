package dev.flrp.espresso.hooks.spawner;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public interface SpawnerProvider {

    boolean isEnabled();

    boolean isSpawner(Block block);

    EntityType getSpawnerType(Block block);

    int getSpawnerStackSize(Block block);

}
