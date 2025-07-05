package dev.flrp.espresso.storage.provider;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SQLiteStorageProvider extends SQLStorageProvider {

    private final File file;

    public SQLiteStorageProvider(Logger logger, File file) {
        super(logger, null, -1, null, null, null, StorageType.SQLITE);
        this.file = file;
    }

    @Override
    public void open() {
        try {
            Class.forName(getDriverClass());
            connection = DriverManager.getConnection(getPathPrefix() + file);
            logger.info("[Storage] " + getName() + " connection opened.");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("[Storage] " + getName() + " failed to open connection", e);
        }
    }

}
