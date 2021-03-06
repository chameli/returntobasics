package com.chameli.rtb.domain.model;

import ch.qos.logback.classic.Level;
import com.chameli.rtb.test.common.fw.guice.DataResource;
import com.chameli.rtb.test.common.fw.guice.GuiceJpaLiquibaseManager;
import com.chameli.rtb.test.common.fw.guice.JpaTestConfig;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
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

        Store store = storeDAO.get(17L);

        ((ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ECLIPSELINK_NAMESPACE + ".sql")).setLevel(Level.DEBUG);
        mgr.getPerformanceProfiler().reset();
        List<Item> founds = dao.findById(1000L, 1001L, 1002L);
        assertEquals(store, getByStore(17L, founds));
        ((ch.qos.logback.classic.Logger) org.slf4j.LoggerFactory.getLogger(ECLIPSELINK_NAMESPACE + ".sql")).setLevel(Level.ERROR);

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
