package com.chameli.rtb.fw.guice.beforeafter;

import java.sql.Connection;

public class DataloaderBeforeAfterContext implements BeforeAfterContext {
    private Connection connection;

    private DataloaderBeforeAfterContext(Connection em) {
        this.connection = em;
    }

    public static DataloaderBeforeAfterContext of(Connection connection) {
        return new DataloaderBeforeAfterContext(connection);
    }

    public Connection getConnection() {
        return connection;
    }
}