package dev.flrp.espresso.hook.stacker;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import dev.rosewood.rosestacker.event.EntityUnstackEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;

public class WildStackerStackerProvider implements StackerProvider {

    private final Plugin plugin;

    public WildStackerStackerProvider(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "WildStacker";
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
        if(!this.isEnabled()) return 1;
        if(WildStackerAPI.getStackedEntity(entity) == null) return 1;
        return WildStackerAPI.getEntityAmount(entity);
    }

}
