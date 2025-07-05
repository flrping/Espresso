package dev.flrp.espresso.storage.provider;

import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;

public interface StorageProvider {

    /**
     * Name of the storage provider.
     * @return The name of the storage provider.
     */
    String getName();

    /**
     * Type of data storage.
     * @return The storage type.
     */
    StorageType getType();

    /**
     * Get the storage behavior.
     * @return The storage behavior.
     */
    StorageBehavior getBehavior();

    /**
     * Checks if the storage provider is actually available for use.
     * @return True if the storage provider is available.
     */
    boolean hasStorage();

}
