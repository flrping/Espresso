package dev.flrp.espresso.hooks.stacker;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.event.EntityUnstackEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class RoseStackerStackerProvider implements StackerProvider {

    private final RoseStackerAPI roseStackerAPI;

    public RoseStackerStackerProvider() {
        roseStackerAPI = isEnabled() ? RoseStackerAPI.getInstance() : null;
    }

    @Override
    public String getName() {
        return "RoseStacker";
    }

    @Override
    public void unregisterEvents(Listener listener, Plugin plugin) {
        EntityUnstackEvent.getHandlerList().unregister(listener);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(roseStackerAPI == null) return 1;
        if(!roseStackerAPI.isEntityStacked(entity)) return 1;
        return roseStackerAPI.getStackedEntity(entity).getStackSize();
    }

}
