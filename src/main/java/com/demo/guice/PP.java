package com.demo.guice;

import com.demo.eclipselink.PersonInformation;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistModule;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.AmbariJpaPersistModule;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.persistence.EntityManager;
import java.beans.PropertyVetoException;
import java.util.*;

import static org.eclipse.persistence.config.PersistenceUnitProperties.*;
import static org.eclipse.persistence.config.PersistenceUnitProperties.DROP_JDBC_DDL_FILE;

/**
 * @author make
 * @creare 10/10/2018
 */
public class PP extends AbstractModule {

    public static void main(String[] args) throws ClassNotFoundException {

//        Injector injector = Guice.createInjector(new JpaPersistModule("default"));
//        Injector injector = Guice.createInjector(new AmbariJpaPersistModule("default"));
        Injector injector = Guice.createInjector(new PP());
        injector.getInstance(PersistService.class).start();

        EntityManager session = injector.getInstance(EntityManager.class);

        session.getTransaction().begin();
        PersonInformation p = new PersonInformation();
        p.setAge(2);
        p.setName("sdf");
//        session.persist(p);


        // insert
        JpaTestEntity testEntity = injector.getInstance(JpaTestEntity.class);
//        testEntity.setId(11L);
        testEntity.setText(new Date().toString());
//        session.persist(testEntity);

        // get
        JpaTestDao jpaTestDao = injector.getInstance(JpaTestDao.class);
        testEntity = jpaTestDao.findById(18l);
        System.out.println(testEntity);

        //update
        testEntity.setText(new Date().toString());
        System.out.println(testEntity);
        jpaTestDao.refresh(testEntity);


        //remove
//        jpaTestDao.remove(testEntity);


//        System.out.println(session.createNamedQuery("tById", JpaTestEntity.class).
//                setParameter("id", 1).getSingleResult());

        session.getTransaction().commit();
        session.close();

    }

    @Override
    protected void configure() {
//        install(new JpaPersistModule("default"));

//        install(new AmbariJpaPersistModule("default"));
        install(buildJpaPersistModule());
    }

    private PersistModule buildJpaPersistModule() {
        Configuration configuration = new Configuration();
        PersistenceType persistenceType = configuration.getPersistenceType();
        System.out.println("persistence type: " + persistenceType);
        AmbariJpaPersistModule jpaPersistModule = new AmbariJpaPersistModule("default");

        Properties persistenceProperties = getPersistenceProperties(configuration);

        if (!persistenceType.equals(PersistenceType.IN_MEMORY)) {
            persistenceProperties.setProperty(JDBC_USER, "root");
            persistenceProperties.setProperty(JDBC_PASSWORD, "root");

//            switch (configuration.getJPATableGenerationStrategy()) {
//                case CREATE:
//                    persistenceProperties.setProperty(DDL_GENERATION, CREATE_ONLY);
//                    dbInitNeeded = true;
//                    break;
//                case DROP_AND_CREATE:
//                    persistenceProperties.setProperty(DDL_GENERATION, DROP_AND_CREATE);
//                    dbInitNeeded = true;
//                    break;
//                case CREATE_OR_EXTEND:
//                    persistenceProperties.setProperty(DDL_GENERATION, CREATE_OR_EXTEND);
//                    break;
//                default:
//                    break;
//            }

            persistenceProperties.setProperty(DDL_GENERATION_MODE, DDL_BOTH_GENERATION);
            persistenceProperties.setProperty(CREATE_JDBC_DDL_FILE, "DDL-create.jdbc");
            persistenceProperties.setProperty(DROP_JDBC_DDL_FILE, "DDL-drop.jdbc");
        }

        jpaPersistModule.properties(persistenceProperties);
        return jpaPersistModule;
    }


    public static Properties getPersistenceProperties(Configuration configuration) {
        Properties properties = new Properties();

        // log what database type has been calculated
        Configuration.DatabaseType databaseType = configuration.getDatabaseType();
        System.out.println("database type: " + databaseType);
//        LOG.info("Detected {} as the database type from the JDBC URL", databaseType);

        switch (configuration.getPersistenceType()) {
            case IN_MEMORY:
                properties.setProperty(JDBC_URL, Configuration.JDBC_IN_MEMORY_URL);
                properties.setProperty(JDBC_DRIVER, Configuration.JDBC_IN_MEMORY_DRIVER);
                properties.setProperty(JDBC_USER, Configuration.JDBC_IN_MEMORY_USER);
                properties.setProperty(JDBC_PASSWORD, Configuration.JDBC_IN_MEMORY_PASSWORD);
                properties.setProperty(DDL_GENERATION, CREATE_ONLY);
                properties.setProperty(THROW_EXCEPTIONS, "true");
                break;
            case REMOTE:
                properties.setProperty(JDBC_URL, configuration.getDatabaseUrl());
                properties.setProperty(JDBC_DRIVER, configuration.getDatabaseDriver());
                break;
            case LOCAL:
                properties.setProperty(JDBC_URL, configuration.getLocalDatabaseUrl());
                properties.setProperty(JDBC_DRIVER, Configuration.SERVER_JDBC_DRIVER.getDefaultValue());
                break;
        }

        //allow to override values above
        // custom jdbc driver properties
        Properties customDatabaseDriverProperties = configuration.getDatabaseCustomProperties();
        properties.putAll(customDatabaseDriverProperties);

        // custom persistence properties
        Properties customPersistenceProperties = configuration.getPersistenceCustomProperties();
        properties.putAll(customPersistenceProperties);

        // determine the type of pool to use
        boolean isConnectionPoolingExternal = false;
        Configuration.ConnectionPoolType connectionPoolType = configuration.getConnectionPoolType();
        if (connectionPoolType == Configuration.ConnectionPoolType.C3P0) {
            isConnectionPoolingExternal = true;
        }

        // force the use of c3p0 with MySQL
        if (databaseType == Configuration.DatabaseType.MYSQL) {
            isConnectionPoolingExternal = true;
        }

        // use c3p0
        if (isConnectionPoolingExternal) {
//            LOG.info("Using c3p0 {} as the EclipsLink DataSource",
//                    ComboPooledDataSource.class.getSimpleName());

            // Oracle requires a different validity query
            String testQuery = "SELECT 1";
            if (databaseType == Configuration.DatabaseType.ORACLE) {
                testQuery = "SELECT 1 FROM DUAL";
            }

            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            // attempt to load the driver; if this fails, warn and move on
            try {
                dataSource.setDriverClass(configuration.getDatabaseDriver());
                System.out.println("jdbc driver: " + configuration.getDatabaseDriver());
            } catch (PropertyVetoException pve) {
//                LOG.warn("Unable to initialize c3p0", pve);
                return properties;
            }

            // basic configuration stuff
            dataSource.setJdbcUrl(configuration.getDatabaseUrl());
            dataSource.setUser(configuration.getDatabaseUser());
            dataSource.setPassword(configuration.getDatabasePassword());

            // pooling
            dataSource.setMinPoolSize(configuration.getConnectionPoolMinimumSize());
            dataSource.setInitialPoolSize(configuration.getConnectionPoolMinimumSize());
            dataSource.setMaxPoolSize(configuration.getConnectionPoolMaximumSize());
            dataSource.setAcquireIncrement(configuration.getConnectionPoolAcquisitionSize());
            dataSource.setAcquireRetryAttempts(configuration.getConnectionPoolAcquisitionRetryAttempts());
            dataSource.setAcquireRetryDelay(configuration.getConnectionPoolAcquisitionRetryDelay());

            // validity
            dataSource.setMaxConnectionAge(configuration.getConnectionPoolMaximumAge());
            dataSource.setMaxIdleTime(configuration.getConnectionPoolMaximumIdle());
            dataSource.setMaxIdleTimeExcessConnections(configuration.getConnectionPoolMaximumExcessIdle());
            dataSource.setPreferredTestQuery(testQuery);
            dataSource.setIdleConnectionTestPeriod(configuration.getConnectionPoolIdleTestInternval());

            properties.put(NON_JTA_DATASOURCE, dataSource);
        }

        return properties;
    }

}

