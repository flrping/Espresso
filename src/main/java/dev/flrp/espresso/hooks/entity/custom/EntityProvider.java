package dev.flrp.espresso.hooks.entity.custom;

import org.bukkit.entity.LivingEntity;

public interface EntityProvider {

     /**
     * Not all plugins are exactly custom entities, so only custom entity
     * implementing plugins will get this provider interface.
     * This allows plugins to do unique things with entities that have custom identifiers.
     */

    boolean isEnabled();

    String getCustomEntityName(LivingEntity entity);

    boolean isCustomEntity(LivingEntity entity);

}
