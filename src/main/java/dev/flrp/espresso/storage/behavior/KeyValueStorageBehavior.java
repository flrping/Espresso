package dev.flrp.espresso.storage.behavior;

import java.io.File;
import java.util.Map;

public interface KeyValueStorageBehavior extends StorageBehavior {

    /**
     * Gets the underlying storage file.
     */
    File getFile();

    /**
     * Sets a value associated with the given key.
     */
    void set(String key, Object value);

    /**
     * Gets a value for the given key.
     */
    Object get(String key);

    /**
     * Gets a typed value for the given key.
     */
    <T> T get(String key, Class<T> type);

    Map<String, Object> getAll();

    void setAll(Map<String, Object> values);

    /**
     * Checks if the key exists in storage.
     */
    boolean has(String key);

    /**
     * Removes a key from storage.
     */
    void remove(String key);

    void save();

}
