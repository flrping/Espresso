package dev.flrp.espresso.storage.provider;

import dev.flrp.espresso.configuration.Configuration;
import dev.flrp.espresso.storage.behavior.KeyValueStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.exception.ProviderException;

import jakarta.annotation.Nullable;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class YAMLStorageProvider implements StorageProvider, KeyValueStorageBehavior {

    private final Configuration config;
    private final Logger logger;

    public YAMLStorageProvider(Logger logger, Configuration config) {
        this.logger = Objects.requireNonNull(logger, "logger cannot be null");
        this.config = Objects.requireNonNull(config, "config cannot be null");
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
    public void open() throws ProviderException {
        try {
            config.load();
            logger.info("[Storage] " + getName() + " file opened.");
        } catch (Exception e) {
            throw new ProviderException("[Storage] " + getName() + " failed to open file", e);
        }
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
    @Nullable
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
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> type) {
        Object val = config.getConfiguration().get(key);
        if (val == null) return null;
        if (!type.isInstance(val)) {
            throw new IllegalStateException("Value at key '" + key + "' is of type " + val.getClass().getName() + ", expected " + type.getName());
        }
        return type.cast(val);
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
