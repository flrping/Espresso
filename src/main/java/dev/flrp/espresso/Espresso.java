package dev.flrp.espresso;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class Espresso extends JavaPlugin {

    private static Espresso instance;

    @Override
    public void onEnable() {
        instance = this;

        new Metrics(this, 19796);
    }

    public static Espresso getInstance() {
        return instance;
    }

}
