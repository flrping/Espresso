package dev.flrp.espresso.hooks.stacker;

import com.craftaro.ultimatestacker.api.UltimateStackerApi;
import com.craftaro.ultimatestacker.api.events.entity.EntityStackKillEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class UltimateStackerStackerProvider implements StackerProvider {

    @Override
    public String getName() {
        return "UltimateStacker";
    }

    @Override
    public void unregisterEvents(Listener listener, Plugin plugin) {
        EntityStackKillEvent.getHandlerList().unregister(listener);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(!this.isEnabled()) return 1;
        if(!UltimateStackerApi.getEntityStackManager().isStackedEntity(entity)) return 1;
        return UltimateStackerApi.getEntityStackManager().getStackedEntity(entity).getAmount();
    }

}
