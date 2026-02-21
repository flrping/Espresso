package dev.flrp.espresso.hook.entity;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public interface Modified extends Hook {

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.ENTITY;
    }

    /**
     * Checks if the entity has a specific modifier by another plugin.
     *
     * @param entity The entity to check.
     * @param modifier The modifier identifier to check for.
     * @return if the entity has the modifier.
     */
    boolean hasModifier(LivingEntity entity, String modifier);

    /**
     * Checks if the entity has custom modifiers by another plugin.
     *
     * @param entity The entity to check.
     * @return if the entity has custom modifiers.
     */
    boolean hasModifiers(LivingEntity entity);

    /**
     * Returns a list of modifiers the entity has.
     *
     * @param entity The entity to check.
     * @return a list of modifiers the entity has.
     */
    List<String> getEntityModifiers(LivingEntity entity);

    /**
     * Returns a list of modifiers that can be applied to entities.
     *
     * @return a list of modifiers that can be applied to entities.
     */
    List<String> getModifierList();

}
