package com.chameli.rtb.test.common.fw.guice.beforeafter;

import java.sql.Connection;

public class DatabaseCreatorBeforeAfterContext implements BeforeAfterContext {
    private Connection connection;

    private DatabaseCreatorBeforeAfterContext(Connection em) {
        this.connection = em;
    }

    public static DatabaseCreatorBeforeAfterContext of(Connection connection) {
        return new DatabaseCreatorBeforeAfterContext(connection);
    }

    public Connection getConnection() {
        return connection;
    }

}