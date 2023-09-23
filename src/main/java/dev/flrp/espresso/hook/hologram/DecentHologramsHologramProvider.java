package dev.flrp.espresso.hook.hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

import java.util.*;

public class DecentHologramsHologramProvider implements HologramProvider {

    private final Set<String> hologramIDs = new HashSet<>();

    @Override
    public String getName() {
        return "DecentHolograms";
    }

    @Override
    public void createHologram(String id, Location location, String... lines) {
        Location spawnLocation = location.clone().add(0.5, 0.5, 0.5);

        List<String> linesList = new ArrayList<>();
        Collections.addAll(linesList, lines);

        DHAPI.createHologram(id, spawnLocation, linesList);
        hologramIDs.add(id);
    }

    @Override
    public void moveHologram(String id, Location location) {
        if(!exists(id)) return;
        Hologram hologram = DHAPI.getHologram(id);
        hologram.setLocation(location);
    }

    @Override
    public void removeHologram(String id) {
        if(!exists(id)) return;
        Hologram hologram = DHAPI.getHologram(id);
        hologram.delete();
        hologramIDs.remove(id);
    }

    @Override
    public void editLine(String id, int line, String text) {
        if(!exists(id)) return;
        DHAPI.getHologram(id).getPage(0).setLine(line, text);
    }

    @Override
    public void removeHolograms() {
        hologramIDs.forEach(this::removeHologram);
    }

    @Override
    public boolean exists(String id) {
        if(!isEnabled()) return false;
        return hologramIDs.contains(id) && DHAPI.getHologram(id) != null;
    }

}
