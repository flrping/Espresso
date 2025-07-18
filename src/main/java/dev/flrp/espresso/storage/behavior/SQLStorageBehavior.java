package dev.flrp.espresso.storage.behavior;

import dev.flrp.espresso.storage.exception.ProviderException;
import dev.flrp.espresso.storage.exception.SQLConsumer;
import dev.flrp.espresso.storage.exception.SQLFunction;
import dev.flrp.espresso.storage.exception.SQLTransaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


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
    void query(String query) throws ProviderException;

    /**
     * Run a query on the database with parameters.
     * @param query The query to run.
     * @param params The parameters to use in the query.
     */
    void query(String query, List<Object> params) throws ProviderException;

    /**
     * Run a query on the database and map the result set to a list of objects. This is a high level abstraction that automatically
     * iterates over the result set and maps each row to an object. You do not need to call {@link ResultSet#next()} or
     * {@link ResultSet#close()} manually.
     *
     * @param query The query to run.
     * @param mapper The mapper to use to map the result set to a list of objects.
     * @throws ProviderException If the query or processing fails.
     * @return The list of objects.
     */
    <T> List<T> queryMap(String query, SQLFunction<ResultSet, T> mapper) throws ProviderException;

    /**
     * Run a query on the database and map the result set to a list of objects. This is a high level abstraction that automatically
     * iterates over the result set and maps each row to an object. You do not need to call {@link ResultSet#next()} or
     * {@link ResultSet#close()} manually.
     *
     * @param query The query to run.
     * @param params The parameters to use in the query.
     * @param mapper The mapper to use to map the result set to a list of objects.
     * @throws ProviderException If the query or processing fails.
     * @return The list of objects.
     */
    <T> List<T> queryMap(String query, List<Object> params, SQLFunction<ResultSet, T> mapper) throws ProviderException;

    /**
     * Run a query on the database and expose the raw {@link ResultSet} to the caller.
     * This is a low-level abstraction that gives full control over iteration and reading.
     *
     * <p>Unlike {@link #queryMap(String, SQLFunction)} or its parameterized variant, this method does not handle row iteration.
     * You must call {@link ResultSet#next()} manually and handle any mapping logic yourself.
     * The {@link ResultSet} and {@link PreparedStatement} are automatically closed after the consumer runs.</p>
     *
     * @param query The query to run.
     * @param consumer A consumer that receives the raw {@link ResultSet} to iterate and process as needed.
     * @throws ProviderException If the query or processing fails.
     */
    void queryEach(String query, SQLConsumer<ResultSet> consumer) throws ProviderException;

    /**
     * Run a parameterized query on the database and expose the raw {@link ResultSet} to the caller.
     * Like {@link #queryEach(String, SQLConsumer)}, but with query parameters.
     *
     * @param query The query to run.
     * @param params Parameters to bind in the prepared statement.
     * @param consumer A consumer that receives the raw {@link ResultSet}.
     * @throws ProviderException If the query or processing fails.
     */
    void queryEach(String query, List<Object> params, SQLConsumer<ResultSet> consumer) throws ProviderException;

    /**
     * Run a transaction on the database.
     * @param action The action to run in the transaction.
     */
    void transaction(SQLTransaction action) throws ProviderException;

}