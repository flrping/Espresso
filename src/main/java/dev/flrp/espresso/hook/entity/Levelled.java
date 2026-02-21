package dev.flrp.espresso.hook.entity;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.entity.LivingEntity;

public interface Levelled extends Hook {

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.ENTITY;
    }

    /**
     * Returns if the entity has a level.
     *
     * @param entity The entity to check.
     * @return if the entity has a level.
     */
    boolean hasLevel(LivingEntity entity);

    /**
     * Returns the level of the LevelledMob.
     *
     * @param entity The entity to check.
     * @return the level of the LevelledMob.
     */
    double getLevel(LivingEntity entity);

}
