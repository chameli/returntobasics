package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.ItemEO;
import com.chameli.rtb.service.junit.dao.DataResource;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemDAOFinderTest {
    private static final Logger logger = Logger.getLogger(ItemDAOFinderTest.class);

    @Rule
    @JpaTestConfig
    @DataResource(resource = "item-data.xml")
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemDAO dao;

    @Test
    public void singleGet() {
        ItemEO found = dao.get(1000L);
        assertEquals("My item", found.getName());
    }

    @Test
    public void multipleGet() {

        List<ItemEO> founds = dao.get(1000L, 1001L);

        assertEquals(2, founds.size());
        Iterator<ItemEO> it = founds.iterator();
        ItemEO found1 = it.next();
        assertNotNull(found1);
        ItemEO found2 = it.next();
        assertNotNull(found2);

        logger.debug("Number of queries: " + mgr.getSessionListener().getNumberOfQueries());
    }
}
