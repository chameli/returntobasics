package com.chameli.rtb.domain.model;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GurkaRepoTest {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Test
    void gurka() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit-hsqldb");
        LOGGER.debug("EntityManagerFactory: {}", factory);
        EntityManager em = factory.createEntityManager();
        LOGGER.debug("EntityManager: {}", em);
    }
}
