package com.chameli.rtb.test.common.fw.guice.beforeafter;

import com.chameli.rtb.test.common.fw.guice.ConfigHelper;
import com.chameli.rtb.test.common.fw.guice.DataResource;
import com.chameli.rtb.test.common.fw.guice.GuiceJpaLiquibaseManager.Config;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.logging.LogLevel;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dataloader implements BeforeAfter<DataloaderBeforeAfterContext> {
    private static final Logger logger = LoggerFactory.getLogger(Dataloader.class);

    private List<String> deleteFromTableStatements;

    private DataResource dataResource;

    private Config config;

    public Dataloader(Config config, DataResource dataResource) {
        this.dataResource = dataResource;
        this.config = config;
    }

    @Override
    public void before(DataloaderBeforeAfterContext ctx) {
        if (StringUtils.isNotBlank(dataResource.resource())) {
            if (ConfigHelper.isLiquibased(config)) {
                loadData(ctx);
            } else {
                throw new IllegalArgumentException("Unsupported");
            }
        }
    }

    private void loadData(DataloaderBeforeAfterContext ctx) {
        String resource = dataResource.resource();
        logger.debug("Loading data from {}", resource);
        try {
            Liquibase liquibase = getLiquibase(ctx.getConnection(), resource);
            liquibase.update((String) null);
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    private Liquibase getLiquibase(Connection connection, String changeLogFile) throws LiquibaseException {
        JdbcConnection c = new JdbcConnection(connection);
        Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), c);
        return liquibase;
    }

    @Override
    public void after(DataloaderBeforeAfterContext ctx) {
        deleteFromAllTables(ctx.getConnection());
    }

    private void deleteFromAllTables(Connection connection) {
        logger.debug("Deleting from all tables");
        try (java.sql.Statement statement = connection.createStatement()) {
            deleteFromTables(statement, getDeleteFromTableStatements(connection), 0, connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        logger.debug("Deleted from all tables");
    }

    private void deleteFromTables(java.sql.Statement statement, List<String> stmts, int statementsExecuted,
                                  Connection connection) {
        List<String> goodStatements = new ArrayList<>();
        try {
            for (String str : stmts) {
                logger.debug("Executing {}", str);
                statement.execute(str);
                connection.commit();
                goodStatements.add(str);
            }
        } catch (Exception e) {
            int newStatementsExecuted = goodStatements.size();
            if (goodStatements.size() == statementsExecuted) {
                throw new RuntimeException(e);
            }
            String badStatement = stmts.get(goodStatements.size());
            goodStatements.addAll(stmts.subList(goodStatements.size() + 1, stmts.size()));
            goodStatements.add(badStatement);
            deleteFromTableStatements = goodStatements;
            deleteFromTables(statement, goodStatements, newStatementsExecuted, connection);
        }
    }

    private List<String> getDeleteFromTableStatements(Connection connection) {
        deleteFromTableStatements = new ArrayList<>();
        try (ResultSet tables = connection.getMetaData().getTables(null, null, null, null)) {
            while (tables.next()) {
                String tableType = tables.getString("TABLE_TYPE");
                if ("TABLE".equals(tableType)) {
                    String tablename = tables.getString("TABLE_NAME");
                    if (isOkToDeleteFrom(tablename)) {
                        deleteFromTableStatements.add("delete from " + tablename);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return deleteFromTableStatements;
    }

    private static Set<String> EXCLUDES_TABLE = new HashSet<>();

    static {
        // EXCLUDES_TABLE.add("DATABASECHANGELOG");
        EXCLUDES_TABLE.add("DATABASECHANGELOGLOCK");
        EXCLUDES_TABLE.add("DATABASE_ALREADY_CREATED_TABLE");
    }

    private boolean isOkToDeleteFrom(String tablename) {
        return !EXCLUDES_TABLE.contains(tablename);
    }
}
