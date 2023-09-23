package dev.flrp.espresso.hook.hologram;

import dev.flrp.espresso.hook.Hook;
import dev.flrp.espresso.hook.HookPurpose;
import org.bukkit.Location;

public interface HologramProvider extends Hook {

    @Override
    default HookPurpose getPurpose() {
        return HookPurpose.HOLOGRAM;
    }

    void createHologram(String id, Location location, String... lines);

    void moveHologram(String id, Location location);

    void removeHologram(String id);

    void editLine(String id, int line, String text);

    void removeHolograms();

    boolean exists(String id);

}