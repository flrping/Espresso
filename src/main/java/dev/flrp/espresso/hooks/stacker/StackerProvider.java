package dev.flrp.espresso.hooks.stacker;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public interface StackerProvider {

    /**
     * Check if the stacker hook is enabled.
     *
     * @return true if the hook is enabled.
     */
    boolean isEnabled();

    /**
     * Register the events for the stacker hook.
     */
    default void registerEvents(Listener listener, Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    /**
     * Unregister the events for the stacker hook.
     */
    void unregisterEvents(Listener listener, Plugin plugin);

    /**
     * Get the stack size of an entity.
     *
     * @param entity The entity to check.
     * @return the size of the stack.
     */
    int getStackSize(LivingEntity entity);


}
