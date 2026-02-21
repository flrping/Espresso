package dev.flrp.espresso.hook.spawner;

import com.songoda.ultimatestacker.api.UltimateStackerApi;
import dev.flrp.espresso.Espresso;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import jakarta.annotation.Nullable;

public class UltimateStackerSpawnerProvider implements SpawnerProvider {

    @Override
    public String getName() {
        return "UltimateStacker";
    }

    @Override
    public SpawnerType getType() {
        return SpawnerType.ULTIMATE_STACKER;
    }

    @Override
    public boolean isSpawner(Block block) {
        if (!isEnabled()) return false;
        return UltimateStackerApi.getSpawnerStackManager().isSpawner(block);
    }

    @Override
    @Nullable
    public EntityType getSpawnerEntityType(Block block) {
        if (Espresso.getInstance().getConfig().getBoolean("errors.print"))
            throw new UnsupportedOperationException("UltimateStacker does not support getting the spawner type from it's API.");
        return null;
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if (!isSpawner(block)) return 0;
        return UltimateStackerApi.getSpawnerStackManager().getSpawner(block).getAmount();
    }

}
