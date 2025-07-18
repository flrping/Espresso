package dev.flrp.espresso.storage.provider;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dev.flrp.espresso.storage.behavior.KeyValueStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.exception.ProviderException;

public class JSONStorageProvider implements StorageProvider, KeyValueStorageBehavior {

    private final File file;
    private Map<String, Object> data = new HashMap<>();
    private final Gson gson = new Gson();
    private final Logger logger;

    public JSONStorageProvider(Logger logger, File file) {
        this.file = file;
        this.logger = logger;
    }

    @Override
    public String getName() {
        return "JSON";
    }

    @Override
    public StorageType getType() {
        return StorageType.JSON;
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
        if (file.exists()) {
            try (Reader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<String, Object>>() {}.getType();
                data = gson.fromJson(reader, type);
                logger.info("[Storage] " + getName() + " file opened.");
            } catch (IOException e) {
                throw new ProviderException("[Storage] Failed to load JSON", e);
            }
        }
    }

    @Override
    public void close() throws ProviderException {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new ProviderException("[Storage] Failed to save JSON", e);
        }
    }

    @Override
    public boolean isConnected() {
        return file.exists();
    }
    
    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void set(String key, Object value) {
        data.put(key, value);
    }

    @Override
    public Object get(String key) {
        return data.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        return type.cast(data.get(key));
    }

    @Override
    public Map<String, Object> getAll() {
        return new HashMap<>(data);
    }

    @Override
    public void setAll(Map<String, Object> values) {
        data.putAll(values);
    }

    @Override
    public boolean has(String key) {
        return data.containsKey(key);
    }

    @Override
    public void remove(String key) {
        data.remove(key);
    }

    @Override
    public void save() {
        try {
            close();
        } catch (ProviderException e) {
            logger.warning("[Storage] Failed to save JSON: " + e.getMessage());
        }
    }
}
