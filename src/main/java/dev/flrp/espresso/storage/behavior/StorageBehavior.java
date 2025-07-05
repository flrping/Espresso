package dev.flrp.espresso.storage.behavior;

public interface StorageBehavior {

    /**
     * Opens the storage connection.
     */
    void open();

    /**
     * Closes the storage connection.
     */
    void close();

    /**
     * Checks if the storage is connected.
     * @return True if the storage is connected, false otherwise.
     */
    boolean isConnected();
    
}
