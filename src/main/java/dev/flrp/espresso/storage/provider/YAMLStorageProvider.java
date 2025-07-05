package dev.flrp.espresso.storage.provider;

import java.io.File;
import java.util.Map;
import java.util.logging.Logger;

import dev.flrp.espresso.configuration.Configuration;
import dev.flrp.espresso.storage.behavior.KeyValueStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;

public class YAMLStorageProvider implements StorageProvider, KeyValueStorageBehavior {

    private final Configuration config;
    private final Logger logger;

    public YAMLStorageProvider(Logger logger, Configuration config) {
        this.config = config;
        this.logger = logger;
    }

    @Override
    public String getName() {
        return "YAML";
    }

    @Override
    public StorageType getType() {
        return StorageType.YAML;
    }

    @Override
    public StorageBehavior getBehavior() {
        return this;
    }

    @Override
    public boolean hasStorage() {
        return true;
    }

    @Override
    public void open() {
        config.load();
        logger.info("[Storage] " + getName() + " file opened.");
    }

    @Override
    public void close() {
        config.save();
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public File getFile() {
        return null;
    }

    @Override
    public void set(String key, Object value) {
        config.getConfiguration().set(key, value);
    }

    @Override
    public Object get(String key) {
        return config.getConfiguration().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        return type.cast(config.getConfiguration().get(key));
    }

    @Override
    public Map<String, Object> getAll() {
        return config.getConfiguration().getValues(false);
    }

    @Override
    public void setAll(Map<String, Object> values) {
        for (Map.Entry<String, Object> entry : values.entrySet()) { 
            config.getConfiguration().set(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public boolean has(String key) {
        return config.getConfiguration().contains(key);
    }

    @Override
    public void remove(String key) {
        config.getConfiguration().set(key, null);
    }

    @Override
    public void save() {
        config.save();
    }
    
}
