package dev.flrp.espresso.hooks.entity.custom;

import dev.flrp.espresso.hooks.Hook;
import org.bukkit.entity.LivingEntity;

public interface EntityProvider extends Hook {

     // Not all plugins are exactly custom entities, some modify or level them with
     // no addition of a new identifier, so only custom entity
     // implementing plugins will get this provider interface. Specifically, plugins
     // that use their own managers to handle custom entity names, identifiers, and such.
     // This allows plugins to do unique things with entities that have custom identifiers.
     // They have a higher likely-hood of having custom models and more.

    /**
     * Get the custom entity name, usually its personal identifier from the plugin.
     *
     * @param entity The entity to get the name of.
     * @return The name of the custom entity.
     */
    String getCustomEntityName(LivingEntity entity);

    /**
     * Checks if the entity is a custom entity.
     *
     * @param entity The entity to check.
     * @return Whether the entity is a custom entity.
     */
    boolean isCustomEntity(LivingEntity entity);

}
