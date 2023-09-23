package dev.flrp.espresso.hook.stacker;

import com.craftaro.ultimatestacker.api.UltimateStackerApi;
import com.craftaro.ultimatestacker.api.events.entity.EntityStackKillEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class UltimateStackerStackerProvider implements StackerProvider {

    private final Plugin plugin;

    public UltimateStackerStackerProvider(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "UltimateStacker";
    }

    @Override
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void unregisterEvents() {
        EntityStackKillEvent.getHandlerList().unregister(this);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(!this.isEnabled()) return 1;
        if(!UltimateStackerApi.getEntityStackManager().isStackedEntity(entity)) return 1;
        return UltimateStackerApi.getEntityStackManager().getStackedEntity(entity).getAmount();
    }

}
