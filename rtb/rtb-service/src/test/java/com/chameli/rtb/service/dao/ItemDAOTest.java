package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.CarItemEO;
import com.chameli.rtb.service.entity.ItemEO;
import com.chameli.rtb.service.entity.StoreEO;
import com.chameli.rtb.service.junit.dao.GuiceJpaLiquibaseManager;
import com.google.inject.Inject;
import org.junit.Rule;
import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ItemDAOTest {

    @Rule
    @JpaTestConfig
    public GuiceJpaLiquibaseManager mgr = new GuiceJpaLiquibaseManager();

    @Inject
    private ItemDAO dao;

    @Test
    public void crud() {
        ItemEO item = new CarItemEO(createStore(), "Saab", "97");
        item.setName("Item1");
        dao.persist(item);

        mgr.reset();

        Query query = mgr.getEntityManager().createQuery("select i from ItemEO i where i.name = :name");
        query.setParameter("name", "Item1");
        ItemEO sameItem = (ItemEO) query.getSingleResult();

        mgr.reset();

        assertEquals(1, dao.findAll(ItemEO.class).size());

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
        found = dao.get(item.getId());
        dao.delete(found);

        mgr.reset();

        assertEquals(0, dao.findAll(ItemEO.class).size());
    }

    private StoreEO createStore() {
        StoreEO store = new StoreEO("My Store");
        return store;
    }

    @Test(expected = PersistenceException.class)
    public void nameUniqueness() throws Exception {
        Column annotation = ItemEO.class.getDeclaredField("name").getAnnotation(Column.class);
        annotation.unique();
        ItemEO entity1 = new CarItemEO(createStore(), "Saab", "91");
        entity1.setName("Item1");
        dao.persist(entity1);
        ItemEO entity2 = new CarItemEO(createStore(), "Saab", "90");
        entity2.setName("Item1");
        dao.persist(entity2);
        mgr.reset();
    }
}
