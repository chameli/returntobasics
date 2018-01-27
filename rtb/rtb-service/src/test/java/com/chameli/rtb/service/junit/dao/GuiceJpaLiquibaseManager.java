package com.chameli.rtb.service.junit.dao;

import com.chameli.rtb.service.junit.dao.beforeafter.*;
import com.google.inject.Module;
import org.apache.log4j.Logger;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryImpl;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuiceJpaLiquibaseManager implements MethodRule {

    public enum DdlGeneration {
        DROP_CREATE, NONE, LIQUIBASE;
    }

    @Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Config {

        Class<? extends Module>[] modules();

        String persistenceUnitName();

        boolean sqlExplorer() default false;

        DdlGeneration ddlGeneration() default DdlGeneration.DROP_CREATE;

        String liquibaseChangelog() default "";

    }

    private static final Logger logger = Logger.getLogger(GuiceJpaLiquibaseManager.class);
    private String persistenceUnitName;
    private EntityManagerFactory factory;
    protected EntityManager em;
    protected EntityTransaction tx;

    private Config config;

    protected Object target;

    private Connection connection;
    private Guicer guicer;
    private Dataloader dataloader;
    private DatabaseCreator databaseCreator;
    private JpaSessionListener sessionListener = new JpaSessionListener();

    public GuiceJpaLiquibaseManager() {
        logger.debug("instantiating");
    }

    public void reset() {
        logger.debug("Resetting");
        commitAndClose();
        createAndBegin();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public JpaSessionListener getSessionListener() {
        return sessionListener;
    }

    @Override
    public Statement apply(final Statement base, FrameworkMethod method, final Object target) {
        this.target = target;
        config = getConfig();
        guicer = new Guicer(config.modules(), target);
        if (getDataResource() != null) {
            dataloader = new Dataloader(config, getDataResource());
        }
        databaseCreator = new DatabaseCreator(config);

        logger.info("Running test " + method.getClass().getSimpleName() + "." + method.getName());
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                before();
                logger.debug("Before evaluating base statement");
                List<Throwable> throwables = new ArrayList<Throwable>();
                try {
                    base.evaluate();
                    logger.debug("After evaluating base statement");
                } catch (Throwable t) {
                    throwables.add(t);
                } finally {
                    try {
                        after();
                    } catch (Throwable t) {
                        throwables.add(t);
                    }
                }
                if (!throwables.isEmpty()) {
                    if (throwables.size() == 1) {
                        throw throwables.get(0);
                    } else {
                        // Currently disregarding the exception thrown in the
                        // after() method
                        throw throwables.get(0);
                    }
                }
            }

        };
    }

    protected void before() {
        startupFactory();
        createAndBegin();

        databaseCreator.before(DatabaseCreatorBeforeAfterContext.of(connection));
        if (dataloader != null) {
            dataloader.before(DataloaderBeforeAfterContext.of(connection));
        }
        if (isSqlExplorerEnabled()) {
            openSqlExplorer();
        }
    }

    private void openSqlExplorer() {
        Map<String, Object> properties = em.getProperties();
        String url = (String) properties.get("javax.persistence.jdbc.url");
        org.hsqldb.util.DatabaseManagerSwing.main(new String[]{"--url", url, "--user", "", "--noexit"});
    }

    private void startupFactory() {
        logger.debug("Starting factory");
        Map<String, String> props = new HashMap<String, String>();
        String ddlGeneration;
        switch (config.ddlGeneration()) {
            case DROP_CREATE:
                ddlGeneration = "drop-and-create-tables";
                break;
            default:
                ddlGeneration = "none";
                break;
        }
        props.put("eclipselink.ddl-generation", ddlGeneration);
        factory = Persistence.createEntityManagerFactory(getPersistenceUnitName(), props);
        factory.unwrap(EntityManagerFactoryImpl.class).getServerSession().getEventManager().addListener(sessionListener);
        logger.debug("Factory started");
    }

    private String getPersistenceUnitName() {
        if (persistenceUnitName == null) {
            persistenceUnitName = config.persistenceUnitName();
        }
        return persistenceUnitName;
    }

    private boolean isSqlExplorerEnabled() {
        return config.sqlExplorer();
    }

    private Config getConfig() {
        Field f = getConfigField();
        Class<Config> annotationClass = Config.class;
        if (f == null) {
            throw new IllegalArgumentException("A " + getClass().getName() + " field must exist and be annotated with "
                    + annotationClass.getName());
        }
        Config annotation = getAnnotation(f, annotationClass);
        if (annotation == null) {
            throw new IllegalArgumentException("Field " + f.getName() + " must be annoted with "
                    + annotationClass.getName());
        }
        return annotation;
    }

    private DataResource getDataResource() {
        Field f = getConfigField();
        Class<DataResource> annotationClass = DataResource.class;
        return getAnnotation(f, annotationClass);
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> T getAnnotation(Field f, Class<T> annotationClass) {
        T annotation = f.getAnnotation(annotationClass);
        if (annotation == null) {
            Annotation[] annotations = f.getAnnotations();
            for (Annotation a : annotations) {
                Annotation[] tmp = a.annotationType().getAnnotations();
                for (Annotation atmp : tmp) {
                    if (atmp.annotationType().equals(annotationClass)) {
                        annotation = (T) atmp;
                        break;
                    }
                }
            }
        }
        return annotation;
    }

    private Field getConfigField() {
        try {
            Field[] fields = target.getClass().getFields();
            Field f = null;
            for (Field field : fields) {
                if (field.get(target) == this) {
                    f = field;
                    break;
                }
            }
            return f;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void closeFactory() {
        logger.debug("Closing factory");
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }

    private void createAndBegin() {
        logger.debug("Create and begin");
        if (em != null || tx != null) {
            throw new IllegalStateException("Manager " + em + " and tx " + tx + " should all be null");
        }
        em = factory.createEntityManager();
        logger.debug("Factory created");
        tx = em.getTransaction();
        tx.begin();
        connection = em.unwrap(Connection.class);

        guicer.before(GuicerBeforeAfterContext.of(em));
    }

    private void commitAndClose() {
        logger.debug("Commit and close");
        if (tx != null) {
            try {
                if (!tx.getRollbackOnly()) {
                    tx.commit();
                }
            } finally {
                tx = null;
            }
        }
        if (em != null) {
            try {
                em.close();
            } finally {
                em = null;
            }
        }
        sessionListener.reset();
    }

    protected void after() {
        if (factory != null) {
            commitAndClose();
            if (dataloader != null) {
                dataloader.after(DataloaderBeforeAfterContext.of(connection));
            }
            databaseCreator.after(DatabaseCreatorBeforeAfterContext.of(connection));
            closeFactory();
        }
    }

}
