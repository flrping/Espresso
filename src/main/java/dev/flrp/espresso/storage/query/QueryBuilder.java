package dev.flrp.espresso.storage.query;

import java.util.List;

/**
 * Interface for building SQL queries.
 * This interface defines the methods required to construct a SQL query and retrieve its parameters.
 */

public interface QueryBuilder {

    /**
     * Returns the parameters that will be used in the query.
     *
     * @return a list of parameters
     */
    List<Object> getParameters();

    /**
     * Builds the SQL query as a string.
     *
     * @return the SQL query string
     */
    String build();

}
