package dev.flrp.espresso.storage.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import dev.flrp.espresso.storage.behavior.DocumentStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.exception.ProviderException;

public class MongoDBStorageProvider implements StorageProvider, DocumentStorageBehavior {

    protected MongoClient client;
    protected final Logger logger;

    protected final String host;
    protected final int port;
    protected final String database;

    protected final StorageType storageType;

    public MongoDBStorageProvider(
            Logger logger,
            String host,
            int port,
            String database,
            StorageType storageType
    ) {
        this.logger = logger;
        this.host = host;
        this.port = port;
        this.database = database;
        this.storageType = storageType;
    }

    public MongoDBStorageProvider(
            String host,
            int port,
            String database,
            StorageType storageType
    ) {
        this(
                Logger.getLogger(MongoDBStorageProvider.class.getName() + "@" + storageType.getName()),
                host,
                port,
                database,
                storageType
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return storageType.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageType getType() {
        return storageType;
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
        try {
            Class.forName(getDriverClass());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDriverClass() {
        return storageType.getDriver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPathPrefix() {
        return storageType.getPathPrefix();
    }

    /**
     * Get the database client connection.
     *
     * @return The database client connection.
     * @see MongoClient
     */
    public MongoClient getClient() {
        return client;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open() throws ProviderException {
        open(getPathPrefix() + host + ":" + port + "/" + database);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void open(String connectionUri) throws ProviderException {
        try {
            if (client != null) {
                throw new ProviderException("MongoDB client is already connected! Please close it before opening a new connection.");
            }
            client = MongoClients.create(connectionUri);
        } catch (Exception e) {
            throw new ProviderException("Failed to connect to MongoDB database", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws ProviderException {
        try {
            if (client != null) {
                client.close();
            }
        } catch (Exception e) {
            throw new ProviderException("Failed to close MongoDB client", e);
        } finally {
            client = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnected() {
        if (client == null) {
            return false;
        }
        try {
            client.getDatabase(database).runCommand(new Document("ping", 1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertDocument(String collection, Map<String, Object> document) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            client.getDatabase(database)
                    .getCollection(collection)
                    .insertOne(new Document(document));
        } catch (Exception e) {
            throw new ProviderException("Failed to insert document", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDocuments(String collection, Map<String, Object> query, Map<String, Object> update) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            client.getDatabase(database)
                    .getCollection(collection)
                    .updateMany(
                            new Document(query),
                            new Document("$set", new Document(update))
                    );
        } catch (Exception e) {
            throw new ProviderException("Failed to update documents", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> findDocuments(String collection, Map<String, Object> query) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            return client.getDatabase(database)
                    .getCollection(collection)
                    .find(new Document(query))
                    .map(Document::entrySet)
                    .map(entries -> {
                        Map<String, Object> map = new HashMap<>();
                        for (Map.Entry<String, Object> entry : entries) {
                            if (!entry.getKey().equals("_id")) {
                                map.put(entry.getKey(), entry.getValue());
                            }
                        }
                        return map;
                    })
                    .into(new ArrayList<>());
        } catch (Exception e) {
            throw new ProviderException("Failed to find documents", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDocuments(String collection, Map<String, Object> query) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            client.getDatabase(database)
                    .getCollection(collection)
                    .deleteMany(new Document(query));
        } catch (Exception e) {
            throw new ProviderException("Failed to delete documents", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createCollection(String collection) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            client.getDatabase(database).createCollection(collection);
        } catch (Exception e) {
            throw new ProviderException("Failed to create collection", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dropCollection(String collection) throws ProviderException {
        if (client == null) {
            throw new ProviderException("MongoDB client is not connected.");
        }
        try {
            client.getDatabase(database).getCollection(collection).drop();
        } catch (Exception e) {
            throw new ProviderException("Failed to drop collection", e);
        }
    }

}
