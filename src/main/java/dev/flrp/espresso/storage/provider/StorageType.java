package dev.flrp.espresso.storage.provider;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import dev.flrp.espresso.storage.dialect.MariaDBStorageDialect;
import dev.flrp.espresso.storage.dialect.MySQLStorageDialect;
import dev.flrp.espresso.storage.dialect.PostgreSQLStorageDialect;
import dev.flrp.espresso.storage.dialect.SQLiteStorageDialect;
import dev.flrp.espresso.storage.dialect.StorageDialect;

public enum StorageType {

    NONE(null, null, null, null),
    SQLITE("org.sqlite.JDBC", "jdbc:sqlite:", "SQLite", SQLiteStorageDialect::new),
    MYSQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://", "MySQL", MySQLStorageDialect::new),
    MARIADB("org.mariadb.jdbc.Driver", "jdbc:mariadb://", "MariaDB", MariaDBStorageDialect::new),
    POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://", "PostgreSQL", PostgreSQLStorageDialect::new),
    JSON(null, null, "JSON", null),
    YAML(null, null, "YAML", null),
    MONGODB("org.mongodb.Driver", "mongodb+srv://", "MongoDB", null);

    private final String driver;
    private final String pathPrefix;
    private final String name;
    private final Supplier<StorageDialect> dialectSupplier;

    StorageType(String driver, String pathPrefix, String name, Supplier<StorageDialect> dialectSupplier) {
        this.driver = driver;
        this.pathPrefix = pathPrefix;
        this.name = name;
        this.dialectSupplier = dialectSupplier;
    }

    /**
     * Returns the driver class name for this storage type.
     *
     * @return The driver class name, or null if not applicable.
     */
    @Nullable
    public String getDriver() {
        return driver;
    }

    /**
     * Returns the path prefix for this storage type.
     *
     * @return The path prefix, or null if not applicable.
     */
    @Nullable
    public String getPathPrefix() {
        return pathPrefix;
    }

    /**
     * Returns the name of this storage type.
     *
     * @return The name of the storage type, or null if not applicable.
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Returns the dialect for this storage type.
     *
     * @return The storage dialect, or null if not applicable.
     */
    @Nullable
    public StorageDialect getDialect() {
        return dialectSupplier != null ? dialectSupplier.get() : null;
    }

    /**
     * Returns the StorageType by its name.
     *
     * @param string The name of the storage type.
     * @return The StorageType corresponding to the name, or NONE if not found.
     */
    public static StorageType getByName(String string) {
        for (StorageType type : values()) {
            if (type.name().equalsIgnoreCase(string)) {
                return type;
            }
        }
        return NONE;
    }

}
