package dev.flrp.espresso.hook.stacker;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;

public interface StackerProvider extends Hook, Listener {

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.STACKER;
    }

    /**
     * Register the events for the stacker hook.
     */
    void registerEvents();

    /**
     * Unregister the events for the stacker hook.
     */
    void unregisterEvents();

    /**
     * Get the stack size of an entity.
     *
     * @param entity The entity to check.
     * @return the size of the stack.
     */
    int getStackSize(LivingEntity entity);

}
