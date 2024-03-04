package dev.flrp.espresso.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private final Plugin plugin;

    public FileConfiguration fileConfig;
    public File file;
    public String path;

    public Configuration(Plugin plugin, String path) {
        this.plugin = plugin;
        this.path = path;
        load();
    }

    public void load() {
        this.file = new File(plugin.getDataFolder(), path + ".yml");

        if (!file.exists()) {
            if (plugin.getResource(path + ".yml") != null) {
                plugin.saveResource(path + ".yml", false);
            } else {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (IOException e) {
                    plugin.getLogger().warning("Failed to create " + path + ".yml");
                }
            }
        }

        this.fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Failed to save in " + path + ".yml");
        }
    }

    public FileConfiguration getConfiguration() {
        return fileConfig;
    }

    public File getFile() {
        return file;
    }

    public String getPath() {
        return path;
    }

    public boolean exists(String path) {
        return fileConfig.getConfigurationSection(path) != null;
    }

}
