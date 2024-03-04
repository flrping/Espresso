package dev.flrp.espresso.hook.stacker;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import uk.antiperson.stackmob.StackMob;
import uk.antiperson.stackmob.events.StackDeathEvent;

public class StackMobStackerProvider implements StackerProvider {

    private final Plugin plugin;
    private final StackMob stackMob;

    public StackMobStackerProvider(Plugin plugin) {
        stackMob = isEnabled() ? (StackMob) Bukkit.getPluginManager().getPlugin("StackMob") : null;
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "StackMob";
    }

    @Override
    public StackerType getType() {
        return StackerType.STACK_MOB;
    }

    @Override
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void unregisterEvents() {
        StackDeathEvent.getHandlerList().unregister(this);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(stackMob == null) return 1;
        if(!stackMob.getEntityManager().isStackedEntity(entity)) return 1;
        return stackMob.getEntityManager().getStackEntity(entity).getSize();
    }

}
