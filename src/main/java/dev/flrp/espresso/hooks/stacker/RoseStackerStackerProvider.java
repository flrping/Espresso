package dev.flrp.espresso.hooks.stacker;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.event.EntityUnstackEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RoseStackerStackerProvider implements StackerProvider {

    private final RoseStackerAPI roseStacker = RoseStackerAPI.getInstance();

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("RoseStacker");
    }

    @Override
    public void unregisterEvents(Listener listener, Plugin plugin) {
        EntityUnstackEvent.getHandlerList().unregister(listener);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(!roseStacker.isEntityStacked(entity)) return 1;
        return roseStacker.getStackedEntity(entity).getStackSize();
    }

}
