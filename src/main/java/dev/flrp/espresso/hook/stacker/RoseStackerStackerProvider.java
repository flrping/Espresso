package dev.flrp.espresso.hook.stacker;

import dev.rosewood.rosestacker.api.RoseStackerAPI;
import dev.rosewood.rosestacker.event.EntityUnstackEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class RoseStackerStackerProvider implements StackerProvider {

    private final Plugin plugin;
    private final RoseStackerAPI roseStackerAPI;

    public RoseStackerStackerProvider(Plugin plugin) {
        roseStackerAPI = isEnabled() ? RoseStackerAPI.getInstance() : null;
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "RoseStacker";
    }

    @Override
    public StackerType getType() {
        return StackerType.ROSE_STACKER;
    }

    @Override
    public void registerEvents() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @Override
    public void unregisterEvents() {
        EntityUnstackEvent.getHandlerList().unregister(this);
    }

    @Override
    public int getStackSize(LivingEntity entity) {
        if(roseStackerAPI == null) return 1;
        if(!roseStackerAPI.isEntityStacked(entity)) return 1;
        return roseStackerAPI.getStackedEntity(entity).getStackSize();
    }



}
