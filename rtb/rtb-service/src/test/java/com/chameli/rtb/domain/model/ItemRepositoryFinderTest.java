package com.chameli.rtb.domain.model;

import com.chameli.rtb.fw.guice.DataResource;
import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager;
import com.chameli.rtb.fw.guice.JpaTestConfig;
import com.google.inject.Inject;
import org.apache.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.eclipse.persistence.logging.Slf4jSessionLogger.ECLIPSELINK_NAMESPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemRepositoryFinderTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryFinderTest.class);

    @Rule
    @JpaTestConfig
    @DataResource(resource = "item-data.xml")
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemRepository dao;

    @Inject
    private StoreRepository storeDAO;

    @Test
    public void singleGet() {
        Item found = dao.get(1000L);
        assertEquals("My item", found.getName());
    }

    @Test
    public void multipleGet() {

        org.apache.log4j.Logger.getLogger(ECLIPSELINK_NAMESPACE + ".sql").setLevel(Level.ALL);

        Store store = storeDAO.get(17L);

        List<Item> founds = dao.findById(1000L, 1001L, 1002L);

        assertEquals(store, getByStore(17L, founds));

        assertEquals(2, mgr.getPerformanceProfiler().getNumberOfQueryCalls());

        logger.warn("Do diddely done");
        assertEquals(3, founds.size());
        Iterator<Item> it = founds.iterator();
        Item found1 = it.next();
        assertNotNull(found1);
        Item found2 = it.next();
        assertNotNull(found2);

    }

    private Store getByStore(Long storeId, List<Item> items) {
        Optional<Item> first = items.stream().filter(f -> storeId.equals(f.getStore().getId())).findFirst();
        return first.map(Item::getStore).orElse(null);
    }
}
