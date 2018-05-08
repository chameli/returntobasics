package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.ItemEO;
import com.chameli.rtb.service.entity.StoreEO;
import com.chameli.rtb.service.junit.dao.DataResource;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;
import org.apache.log4j.Level;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

import static org.eclipse.persistence.logging.Slf4jSessionLogger.ECLIPSELINK_NAMESPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemDAOFinderTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemDAOFinderTest.class);

    @Rule
    @JpaTestConfig
    @DataResource(resource = "item-data.xml")
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemDAO dao;

    @Inject
    private StoreDAO storeDAO;

    @Test
    public void singleGet() {
        ItemEO found = dao.get(1000L);
        assertEquals("My item", found.getName());
    }

    @Test
    public void multipleGet() {

        org.apache.log4j.Logger.getLogger(ECLIPSELINK_NAMESPACE + ".sql").setLevel(Level.ALL);

        StoreEO storeEO = storeDAO.get(17L);

        List<ItemEO> founds = dao.findById(1000L, 1001L, 1002L);

        assertEquals(storeEO, getByStore(17L, founds));

        assertEquals(2, mgr.getPerformanceProfiler().getNumberOfQueryCalls());

        logger.warn("Do diddely done");
        assertEquals(3, founds.size());
        Iterator<ItemEO> it = founds.iterator();
        ItemEO found1 = it.next();
        assertNotNull(found1);
        ItemEO found2 = it.next();
        assertNotNull(found2);

    }

    private StoreEO getByStore(Long storeId, List<ItemEO> items) {
        return items.stream().filter(f -> storeId.equals(f.getStore().getId())).findFirst().get().getStore();
    }
}
