package dev.flrp.espresso.hook.spawner;

import me.angeschossen.upgradeablespawners.api.UpgradeableSpawnersAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public class UpgradeableSpawnersSpawnerProvider implements SpawnerProvider {

    @Override
    public String getName() {
        return "UpgradeableSpawners";
    }

    @Override
    public SpawnerType getType() {
        return SpawnerType.UPGRADEABLE_SPAWNERS;
    }

    @Override
    public boolean isSpawner(Block block) {
        if(!isEnabled()) return false;
        return UpgradeableSpawnersAPI.getInstance().getSpawner(block) != null;
    }

    @Override @Nullable
    public EntityType getSpawnerEntityType(Block block) {
        if(!isSpawner(block)) return null;
        return UpgradeableSpawnersAPI.getInstance().getSpawner(block).getEntityType();
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if(!isSpawner(block)) return 0;
        return 1;
    }

}
