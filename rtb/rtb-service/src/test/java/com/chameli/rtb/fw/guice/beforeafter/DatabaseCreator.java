package com.chameli.rtb.fw.guice.beforeafter;

import com.chameli.rtb.fw.guice.ConfigHelper;
import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager.Config;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.logging.LogLevel;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class DatabaseCreator implements BeforeAfter<DatabaseCreatorBeforeAfterContext> {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseCreator.class);

    private static final String DATABASE_ALREADY_CREATED_TABLENAME = "DATABASE_ALREADY_CREATED_TABLE";

    private static final String DATABASE_ALREADY_CREATED_TABLE_SQL = "create table "
            + DATABASE_ALREADY_CREATED_TABLENAME + " (a integer)";

    private Boolean databaseAlreadyCreated;

    private boolean createDatabaseWithLiquibase;

    private String changeLogFile = "changelog.xml";

    public DatabaseCreator(Config config) {
        this.createDatabaseWithLiquibase = ConfigHelper.isLiquibased(config);
    }

    @Override
    public void before(DatabaseCreatorBeforeAfterContext ctx) {
        if (createDatabaseWithLiquibase) {
            if (!isDatabaseAlreadyCreated(ctx.getConnection())) {
                liquibaseCreateDatabase(ctx.getConnection());
                createDatabaseAlreadyCreatedTable(ctx.getConnection());
            }
        }
        deleteAllDataSafely(ctx);
    }

    private void deleteAllDataSafely(DatabaseCreatorBeforeAfterContext ctx) {
        try {
            Connection connection = ctx.getConnection();
            ResultSet rsTables = connection.getMetaData().getTables(null, null, null, null);
            Collection<String> tablenames = new ArrayList<>();
            while (rsTables.next()) {
                tablenames.add(rsTables.getString("TABLE_NAME"));
            }
            deleteSafely(connection, tablenames);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    private void deleteSafely(Connection connection, Collection<String> tablenames) throws SQLException {
        int numEmptiedTables = 0;
        Statement statement = connection.createStatement();
        for (String tablename : tablenames) {
            try {
                int numRows = statement.executeUpdate("DELETE FROM " + tablename);
                if (numRows > 0) {
                    numEmptiedTables++;
                }
            } catch (SQLException e) {
                // Try with next table in case a constraint exception occurred
            }
        }
        if (numEmptiedTables > 0) {
            deleteSafely(connection, tablenames);
        }
    }

    @Override
    public void after(DatabaseCreatorBeforeAfterContext ctx) {
        if (createDatabaseWithLiquibase) {
            liquibaseDropDatabase(ctx.getConnection());
        }
    }

    private void createDatabaseAlreadyCreatedTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(DATABASE_ALREADY_CREATED_TABLE_SQL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDatabaseAlreadyCreated(Connection connection) {
        if (databaseAlreadyCreated != null) {
            return databaseAlreadyCreated;
        }
        logger.debug("Fetching metadata to check if database is already created");
        try (ResultSet tables = connection.getMetaData().getTables(null, null, DATABASE_ALREADY_CREATED_TABLENAME, null)) {
            logger.debug("Fetced metadata");
            databaseAlreadyCreated = tables.next();
            return databaseAlreadyCreated;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void liquibaseCreateDatabase(Connection connection) {
        logger.debug("Creating database with liquibase");
        try {
            Liquibase liquibase = getLiquibase(connection, changeLogFile);
            // LogFactory.getLogger().setLogLevel(LogLevel.DEBUG);
            liquibase.update((String) null);
            logger.debug("Database created");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Liquibase getLiquibase(Connection connection, String changeLogFile) throws LiquibaseException {
        JdbcConnection c = new JdbcConnection(connection);
        Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), c);
        liquibase.getLog().setLogLevel(LogLevel.WARNING);
        return liquibase;
    }

    private void liquibaseDropDatabase(Connection connection) {
        logger.debug("Dropping database with liquibase");
        try {
            getLiquibase(connection, changeLogFile).dropAll();
            logger.debug("Database dropped");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
