package dev.flrp.espresso.hooks.holograms;

import dev.flrp.espresso.hooks.Hook;
import org.bukkit.Location;

public interface HologramProvider extends Hook {

    void createHologram(String id, Location location, String... lines);

    void moveHologram(String id, Location location);

    void removeHologram(String id);

    void editLine(String id, int line, String text);

    void removeHolograms();

    boolean exists(String id);

}