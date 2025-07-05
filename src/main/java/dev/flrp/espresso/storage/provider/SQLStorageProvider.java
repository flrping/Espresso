package dev.flrp.espresso.storage.provider;

import dev.flrp.espresso.storage.behavior.SQLStorageBehavior;
import dev.flrp.espresso.storage.behavior.StorageBehavior;
import dev.flrp.espresso.storage.dialect.SQLStorageDialect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

public class SQLStorageProvider implements StorageProvider, SQLStorageBehavior {

    protected Connection connection;
    protected final Logger logger;

    protected final String host;
    protected final int port;
    protected final String database;
    protected final String user;
    protected final String password;

    protected final StorageType storageType;

    public SQLStorageProvider(
            Logger logger,
            String host,
            int port,
            String database,
            String user,
            String password,
            StorageType storageType
    ) {
        this.logger = logger;
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.storageType = storageType;
    }

    @Override
    public String getName() {
        return storageType.getName();
    }

    @Override
    public StorageType getType() {
        return storageType;
    }

    @Override
    public StorageBehavior getBehavior() {
        return this;
    }

    public SQLStorageDialect getDialect() {
        return (SQLStorageDialect) storageType.getDialect();
    }

    @Override
    public String getDriverClass() {
        return storageType.getDriver();
    }

    @Override
    public String getPathPrefix() {
        return storageType.getPathPrefix();
    }

    @Override
    public boolean hasStorage() {
        try {
            Class.forName(getDriverClass());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override
    public void open() {
        try {
            Class.forName(getDriverClass());
            connection = DriverManager.getConnection(getPathPrefix() + host + ":" + port + "/" + database, user, password);
            logger.info("[Storage] " + getName() + " connection opened.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("[Storage] " + getName() + " failed to open connection", e);
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("[Storage] " + getName() + " connection closed.");
            } catch (SQLException e) {
                throw new RuntimeException("[Storage] " + getName() + " failed to close connection", e);
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void query(String query) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("[Storage] Failed to execute query", e);
        }
    }

    @Override
    public void query(String query, List<Object> params) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("[Storage] Failed to execute query", e);
        }
    }

    @Override
    public <T> List<T> query(String query, Function<ResultSet, T> mapper) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                List<T> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(mapper.apply(rs));
                }

                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException("[Storage] Failed to execute query", e);
        }
    }

    @Override
    public <T> List<T> query(String query, List<Object> params, Function<ResultSet, T> mapper) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = statement.executeQuery()) {
                List<T> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(mapper.apply(rs));
                }

                return results;
            }
        } catch (SQLException e) {
            throw new RuntimeException("[Storage] Failed to execute query", e);
        }
    }

    @Override
    public void transaction(Runnable action) {
        try {
            connection.setAutoCommit(false);
            action.run();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                logger.severe("[Storage] Failed to rollback transaction: " + rollbackEx.getMessage());
            }
            throw new RuntimeException("[Storage] Transaction failed", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.warning("[Storage] Could not reset auto-commit: " + e.getMessage());
            }
        }
    }
}
