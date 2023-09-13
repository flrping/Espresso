package dev.flrp.espresso.hooks;

import org.bukkit.Bukkit;

public interface Hook {

    /**
     * Get the name of the hook.
     *
     * @return the name of the hook.
     */
    String getName();

    /**
     * Check if the hook is enabled.
     *
     * @return true if the hook is enabled.
     */
    default boolean isEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled(getName());
    }

}
