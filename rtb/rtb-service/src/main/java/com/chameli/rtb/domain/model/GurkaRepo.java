package com.chameli.rtb.domain.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GurkaRepo {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public Item findItem(long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit-hsqldb");
        LOGGER.debug("EntityManagerFactory: {}", factory);
        EntityManager em = factory.createEntityManager();
        LOGGER.debug("EntityManager: {}", em);
        return em.find(Item.class, id);
    }

}
