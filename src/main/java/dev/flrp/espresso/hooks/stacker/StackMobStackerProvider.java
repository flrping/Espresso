package dev.flrp.espresso.hooks.stacker;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import uk.antiperson.stackmob.StackMob;
import uk.antiperson.stackmob.events.StackDeathEvent;

public class StackMobStackerProvider implements StackerProvider {

    private final StackMob stackMob;

    public StackMobStackerProvider() {
        stackMob = isEnabled() ? (StackMob) Bukkit.getPluginManager().getPlugin("StackMob") : null;
    }

    @Override
    public String getName() {
        return "StackMob";
    }

    @Override
    public void unregisterEvents(Listener listener, Plugin plugin) {
        StackDeathEvent.getHandlerList().unregister(listener);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(stackMob == null) return 1;
        if(!stackMob.getEntityManager().isStackedEntity(entity)) return 1;
        return stackMob.getEntityManager().getStackEntity(entity).getSize();
    }

}
