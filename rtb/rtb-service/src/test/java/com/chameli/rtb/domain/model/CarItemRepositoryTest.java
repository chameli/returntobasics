package com.chameli.rtb.domain.model;

import com.chameli.rtb.fw.GuiceJpaLiquibaseManager;
import com.chameli.rtb.fw.JpaTestConfig;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CarItemRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryFinderTest.class);

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private CarItemRepository dao;

    @Inject
    private StoreRepository storeDAO;

    @Test
    public void crud() {
        CarItem item = createCarItem("Saab", "93");
        dao.persist(item);

        mgr.reset();

        assertEquals(1, dao.findAll(CarItem.class).size());
    }

    @Test
    public void find() {

        dao.persist(createCarItem("Saab", "Aero"));

        mgr.reset();

        List<CarItem> cars = dao.findByMake("Saab");

    }

    private CarItem createCarItem(String make, String model) {
        CarItem item = new CarItem(createStore(), make, model);
        item.setHorsepowers(120);
        return item;
    }

    private Store createStore() {
        Store store = new Store("My Store");
        storeDAO.persist(store);
        return store;
    }

}
