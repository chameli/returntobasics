package com.chameli.rtb.ejb;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RtbServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);
    }

    public static class EntityManagerProvider implements Provider<EntityManager> {
        private static EntityManagerFactory factory;

        @Override
        public EntityManager get() {
            return getFactory().createEntityManager();
        }

        private EntityManagerFactory getFactory() {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory("persistenceUnit");
            }
            return factory;
        }
    }
}
