package dev.flrp.espresso.storage.behavior;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public interface SQLStorageBehavior extends StorageBehavior {

    /**
     * Get the driver class.
     * @return The driver class.
     */
    String getDriverClass();

    /**
     * Get the path prefix.
     * @return The path prefix.
     */
    String getPathPrefix();

    /**
     * Get the database connection.
     * @return The database connection.
     */
    Connection getConnection();

    /**
     * Run a query on the database.
     * @param query The query to run.
     */
    void query(String query);

    /**
     * Run a query on the database with parameters.
     * @param query The query to run.
     * @param params The parameters to use in the query.
     */
    void query(String query, List<Object> params);

    /**
     * Run a query on the database and map the result set to a list of objects.
     * @param query The query to run.
     * @param mapper The mapper to use to map the result set to a list of objects.
     * @return The list of objects.
     */
    <T> List<T> query(String query, Function<ResultSet, T> mapper);

    /**
     * Run a query on the database with parameters and map the result set to a list of objects.
     * @param query The query to run.
     * @param params The parameters to use in the query.
     * @param mapper The mapper to use to map the result set to a list of objects.
     * @return The list of objects.
     */
    <T> List<T> query(String query, List<Object> params, Function<ResultSet, T> mapper);

    /**
     * Run a transaction on the database.
     * @param action The action to run in the transaction.
     */
    void transaction(Runnable action);

}