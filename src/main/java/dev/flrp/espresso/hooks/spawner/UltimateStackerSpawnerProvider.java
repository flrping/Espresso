package dev.flrp.espresso.hooks.spawner;

import com.craftaro.ultimatestacker.api.UltimateStackerApi;
import dev.flrp.espresso.Espresso;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;

public class UltimateStackerSpawnerProvider implements SpawnerProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("UltimateStacker");
    }

    @Override
    public boolean isSpawner(Block block) {
        return UltimateStackerApi.getSpawnerStackManager().isSpawner(block);
    }

    @Override @Nullable
    public EntityType getSpawnerType(Block block) {
        if(Espresso.getInstance().getConfig().getBoolean("errors.print"))
            throw new UnsupportedOperationException("UltimateStacker does not support getting the spawner type from it's API.");
        return null;
    }

    @Override
    public int getSpawnerStackSize(Block block) {
        if(!isSpawner(block)) return 0;
        return UltimateStackerApi.getSpawnerStackManager().getSpawner(block).getAmount();
    }

}
