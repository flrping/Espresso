package dev.flrp.espresso.storage.behavior;

import dev.flrp.espresso.storage.exception.ProviderException;

public interface StorageBehavior {

    /**
     * Opens the storage connection.
     */
    void open() throws ProviderException;

    /**
     * Closes the storage connection.
     */
    void close() throws ProviderException;

    /**
     * Checks if the storage is connected.
     * @return True if the storage is connected, false otherwise.
     */
    boolean isConnected();
    
}
