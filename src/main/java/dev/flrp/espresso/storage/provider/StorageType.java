package dev.flrp.espresso.storage.provider;

import dev.flrp.espresso.storage.dialect.*;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum StorageType {

    NONE(null, null, null, null),
    SQLITE("org.sqlite.JDBC", "jdbc:sqlite:", "SQLite", SQLiteStorageDialect::new),
    MYSQL("com.mysql.cj.jdbc.Driver", "jdbc:mysql://", "MySQL", MySQLStorageDialect::new),
    MARIADB("org.mariadb.jdbc.Driver", "jdbc:mariadb://", "MariaDB", MariaDBStorageDialect::new),
    POSTGRESQL("org.postgresql.Driver", "jdbc:postgresql://", "PostgreSQL", PostgreSQLStorageDialect::new),
    JSON(null, null, "JSON", null),
    YAML(null, null, "YAML", null);

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

    @Nullable
    public String getDriver() {
        return driver;
    }

    @Nullable
    public String getPathPrefix() {
        return pathPrefix;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public StorageDialect getDialect() {
        return dialectSupplier.get();
    }

    public static StorageType getByName(String string) {
        for (StorageType type : values()) {
            if (type.name().equalsIgnoreCase(string)) {
                return type;
            }
        }
        return NONE;
    }

}
