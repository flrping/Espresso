package dev.flrp.espresso.hooks.stacker;

import dev.flrp.espresso.hooks.Hook;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public interface StackerProvider extends Hook {

    /**
     * Register the events for the stacker hook.
     *
     * @param listener The listener to register.
     * @param plugin The plugin to register the listener for.
     */
    default void registerEvents(Listener listener, Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    /**
     * Unregister the events for the stacker hook.
     *
     * @param listener The listener to unregister.
     * @param plugin The plugin to unregister the listener for.
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
