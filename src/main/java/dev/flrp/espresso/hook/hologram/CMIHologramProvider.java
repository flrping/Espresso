package dev.flrp.espresso.hook.hologram;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CMIHologramProvider implements HologramProvider {

    @Override
    public HologramType getType() {
        return HologramType.CMI;
    }

    @Override
    public void createHologram(String id, Location location, String... lines) {
        Location spawnLocation = location.clone().add(0.5, 0.5, 0.5);

        List<String> linesList = new ArrayList<>();
        Collections.addAll(linesList, lines);

        CMIHologram hologram = new CMIHologram(id, spawnLocation);
        hologram.setLines(linesList);
    }

    @Override
    public void createHologram(String id, Location location, List<String> lines) {
        Location spawnLocation = location.clone().add(0.5, 0.5, 0.5);

        CMIHologram hologram = new CMIHologram(id, spawnLocation);
        hologram.setLines(lines);
    }

    @Override
    public void moveHologram(String id, Location location) {
        if(!exists(id)) return;
        CMIHologram hologram = CMI.getInstance().getHologramManager().getHolograms().get(id);
        hologram.moveTo(location);
    }

    @Override
    public void removeHologram(String id) {
        if(!exists(id)) return;
        CMIHologram hologram = CMI.getInstance().getHologramManager().getHolograms().get(id);
        hologram.remove();
    }

    @Override
    public void editLine(String id, int line, String text) {
        if(!exists(id)) return;
        CMIHologram hologram = CMI.getInstance().getHologramManager().getHolograms().get(id);
        List<String> lines = hologram.getLines();
        if(line < 0 || line >= lines.size()) return;
        lines.set(line, text);
        hologram.setLines(lines);
    }

    @Override
    public void removeHolograms() {
        if (!isEnabled()) return;
        for(CMIHologram hologram : CMI.getInstance().getHologramManager().getHolograms().values()) {
            hologram.remove();
        }
    }

    @Override
    public boolean exists(String id) {
        if(!isEnabled()) return false;
        return CMI.getInstance().getHologramManager().getHolograms().containsKey(id);
    }

    @Override
    public String getName() {
        return "CMI";
    }

}
