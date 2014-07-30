package com.chameli.rtb.service.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import javax.persistence.Column;
import javax.persistence.PersistenceException;

import org.junit.Rule;
import org.junit.Test;

import com.chameli.rtb.service.entity.ItemEO;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;

public class ItemDAOTest {

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemDAO dao;

    @Test
    public void crud() {
        ItemEO item = new ItemEO();
        item.setName("Item1");
        dao.persist(item);

        mgr.reset();

        assertEquals(1, dao.findAll().size());

        mgr.reset();

        ItemEO found = dao.get(item.getId());
        assertNotSame(item, found);
        assertEquals(item.getId(), found.getId());

        String newName = "Another item";
        found.setName(newName);

        mgr.reset();

        found = dao.get(item.getId());
        assertEquals(newName, found.getName());

        mgr.reset();
        dao.delete(item.getId());

        mgr.reset();

        assertEquals(0, dao.findAll().size());
    }

    @Test(expected = PersistenceException.class)
    public void nameUniqueness() throws Exception {
        Column annotation = ItemEO.class.getDeclaredField("name").getAnnotation(Column.class);
        annotation.unique();
        ItemEO entity1 = new ItemEO();
        entity1.setName("Item1");
        dao.persist(entity1);
        ItemEO entity2 = new ItemEO();
        entity2.setName("Item1");
        dao.persist(entity2);
        mgr.reset();
    }
}
