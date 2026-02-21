package dev.flrp.espresso.storage.behavior;

import dev.flrp.espresso.storage.exception.ProviderException;

import java.util.List;
import java.util.Map;

public interface DocumentStorageBehavior extends StorageBehavior {

    /**
     * Opens the storage connection with specific options.
     *
     * @param connectionUri The connection URI or options to open the storage.
     * @throws ProviderException If an error occurs while opening the storage.
     */
    void open(String connectionUri) throws ProviderException;

    /**
     * Get the driver class.
     *
     * @return The driver class.
     */
    String getDriverClass();

    /**
     * Get the path prefix.
     *
     * @return The path prefix.
     */
    String getPathPrefix();

    /**
     * Inserts a document into the specified collection.
     *
     * @param collection The name of the collection.
     * @param document   The document to insert.
     * @throws ProviderException If an error occurs while inserting the document.
     */
    void insertDocument(String collection, Map<String, Object> document) throws ProviderException;

    /**
     * Updates documents in the specified collection that match the query.
     *
     * @param collection The name of the collection.
     * @param query      The query to match documents.
     * @param update     The update to apply to matched documents.
     * @throws ProviderException If an error occurs while updating documents.
     */
    void updateDocuments(String collection, Map<String, Object> query, Map<String, Object> update) throws ProviderException;

    /**
     * Finds documents in the specified collection that match the query.
     *
     * @param collection The name of the collection.
     * @param query      The query to match documents.
     * @return A list of documents that match the query.
     * @throws ProviderException If an error occurs while finding documents.
     */
    List<Map<String, Object>> findDocuments(String collection, Map<String, Object> query) throws ProviderException;

    /**
     * Deletes documents in the specified collection that match the query.
     *
     * @param collection The name of the collection.
     * @param query      The query to match documents for deletion.
     * @throws ProviderException If an error occurs while deleting documents.
     */
    void deleteDocuments(String collection, Map<String, Object> query) throws ProviderException;

    /**
     * Creates a new collection with the specified name.
     *
     * @param collection The name of the collection to create.
     * @throws ProviderException If an error occurs while creating the collection.
     */
    void createCollection(String collection) throws ProviderException;

    /**
     * Drops the specified collection.
     *
     * @param collection The name of the collection to drop.
     * @throws ProviderException If an error occurs while dropping the collection.
     */
    void dropCollection(String collection) throws ProviderException;

}
