package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.CarItemEO;
import com.chameli.rtb.service.entity.StoreEO;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarItemDAOTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemDAOFinderTest.class);

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private CarItemDAO dao;

    @Inject
    private StoreDAO storeDAO;

    @Test
    public void crud() {
        CarItemEO item = createCarItem("Saab", "93");
        dao.persist(item);

        mgr.reset();

        assertEquals(1, dao.findAll(CarItemEO.class).size());
    }

    @Test
    public void find() {

        dao.persist(createCarItem("Saab", "Aero"));

        mgr.reset();

        List<CarItemEO> cars = dao.findByMake("Saab");

    }

    private CarItemEO createCarItem(String make, String model) {
        CarItemEO item = new CarItemEO(createStore(), make, model);
        item.setHorsepowers(120);
        return item;
    }

    private StoreEO createStore() {
        StoreEO store = new StoreEO("My Store");
        storeDAO.persist(store);
        return store;
    }

}
