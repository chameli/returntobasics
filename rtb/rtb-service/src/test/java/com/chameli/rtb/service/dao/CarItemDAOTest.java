package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.CarItemEO;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarItemDAOTest {

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemDAO dao;

    @Test
    public void crud() {
        CarItemEO item = new CarItemEO("Saab", "Aero");
        item.setHorsepowers(120);
        dao.persist(item);

        mgr.reset();

        assertEquals(1, dao.findAll().size());
    }

}
