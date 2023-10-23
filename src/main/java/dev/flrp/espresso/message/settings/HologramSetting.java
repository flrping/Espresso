package dev.flrp.espresso.message.settings;

import dev.flrp.espresso.hook.hologram.HologramProvider;
import org.bukkit.plugin.Plugin;

public class HologramSetting {

    private final Plugin plugin;

    private HologramProvider hologramProvider;
    private int duration;

    public HologramSetting(Plugin plugin) {
        this.plugin = plugin;
    }

    public HologramProvider getHologramProvider() {
        return hologramProvider;
    }

    public void setHologramProvider(HologramProvider hologramProvider) {
        this.hologramProvider = hologramProvider;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Plugin getPlugin() {
        return plugin;
    }

}
