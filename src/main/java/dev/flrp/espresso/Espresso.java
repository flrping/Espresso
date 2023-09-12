package dev.flrp.espresso;

import org.bukkit.plugin.java.JavaPlugin;

public final class Espresso extends JavaPlugin {

    private static Espresso instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Espresso getInstance() {
        return instance;
    }

}
