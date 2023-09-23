package dev.flrp.espresso.hook.spawner;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

public interface SpawnerProvider extends Hook {

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.SPAWNER;
    }

    /**
     * Check if the block is a spawner.
     *
     * @param block Block to check.
     * @return true if the block is a spawner.
     */
    boolean isSpawner(Block block);

    /**
     * Get the spawner type.
     *
     * @param block Block to check.
     * @return The type of entity the spawner spawns.
     */
    EntityType getSpawnerEntityType(Block block);

    /**
     * Get the spawner stack size.
     *
     * @param block Block to check.
     * @return Spawner stack size.
     */
    int getSpawnerStackSize(Block block);

}
