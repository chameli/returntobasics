package com.chameli.rtb.domain.model;

import com.chameli.rtb.fw.guice.GuiceJpaLiquibaseManager;
import com.chameli.rtb.fw.guice.JpaTestConfig;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ItemRepositoryTest {

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemRepository dao;

    @Inject
    private StoreRepository storeDAO;

    @Test
    public void crud() {
        Item item = new CarItem(createStore(), "Saab", "97");
        item.setName("Item1");
        dao.persist(item);

        mgr.reset();

        Query query = mgr.getEntityManager().createQuery("select i from Item i where i.name = :name");
        query.setParameter("name", "Item1");
        Item sameItem = (Item) query.getSingleResult();

        mgr.reset();

        assertEquals(1, dao.findAll(Item.class).size());

        mgr.reset();

        Item found = dao.get(item.getId());
        assertNotSame(item, found);
        assertEquals(item.getId(), found.getId());

        String newName = "Another item";
        found.setName(newName);

        mgr.reset();

        found = dao.get(item.getId());
        assertEquals(newName, found.getName());

        mgr.reset();
        found = dao.get(item.getId());
        dao.delete(found);

        mgr.reset();

        assertEquals(0, dao.findAll(Item.class).size());
    }

    private Store createStore() {
        Store store = new Store("My Store");
        storeDAO.persist(store);
        return store;
    }

    @Test(expected = PersistenceException.class)
    public void nameUniqueness() throws Exception {
        Column annotation = Item.class.getDeclaredField("name").getAnnotation(Column.class);
        annotation.unique();
        Item entity1 = new CarItem(createStore(), "Saab", "91");
        entity1.setName("Item1");
        dao.persist(entity1);
        Item entity2 = new CarItem(createStore(), "Saab", "90");
        entity2.setName("Item1");
        dao.persist(entity2);
        mgr.reset();
    }
}
