package dev.flrp.espresso.hooks.stacker;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import dev.rosewood.rosestacker.event.EntityUnstackEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class WildStackerStackerProvider implements StackerProvider {

    @Override
    public boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("WildStacker");
    }

    @Override
    public void unregisterEvents(Listener listener, Plugin plugin) {
        EntityUnstackEvent.getHandlerList().unregister(listener);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(WildStackerAPI.getStackedEntity(entity) == null) return 1;
        return WildStackerAPI.getEntityAmount(entity);
    }

}
