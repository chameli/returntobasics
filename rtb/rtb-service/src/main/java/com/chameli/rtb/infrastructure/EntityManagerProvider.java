package com.chameli.rtb.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProvider {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @PersistenceContext(unitName = "rtb")
    @Produces
    private EntityManager em;

    public EntityManagerProvider() {
        LOGGER.debug("Creating instance");
    }
}
