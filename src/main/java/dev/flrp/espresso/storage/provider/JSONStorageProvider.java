package dev.flrp.espresso.storage.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dev.flrp.espresso.storage.behavior.KeyValueStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.exception.ProviderException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class JSONStorageProvider implements StorageProvider, KeyValueStorageBehavior {

    private final File file;
    private Map<String, Object> data = new HashMap<>();
    private final Gson gson = new Gson();
    private final Logger logger;

    public JSONStorageProvider(Logger logger, File file) {
        this.file = file;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "JSON";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageType getType() {
        return StorageType.JSON;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageBehavior getBehavior() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasStorage() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() throws ProviderException {
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, Object>>() {
                }.getType();
                data = gson.fromJson(reader, type);
                logger.info("[Storage] " + getName() + " file opened.");
            } catch (IOException e) {
                throw new ProviderException("[Storage] Failed to load JSON", e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws ProviderException {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new ProviderException("[Storage] Failed to save JSON", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        return file.exists();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getFile() {
        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(String key, Object value) {
        data.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(String key) {
        return data.get(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getAll() {
        return new HashMap<>(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAll(Map<String, Object> values) {
        data.putAll(values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean has(String key) {
        return data.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(String key) {
        data.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        try {
            close();
        } catch (ProviderException e) {
            logger.warning("[Storage] Failed to save JSON: " + e.getMessage());
        }
    }
}
