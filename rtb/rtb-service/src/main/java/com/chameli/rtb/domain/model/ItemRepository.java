package com.chameli.rtb.domain.model;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ItemRepository extends AbstractRepository {

    public <T> List<T> findById(Long... ids) {
        Query query = em().createNamedQuery("findById");
        query.setParameter("ids", Arrays.asList(ids));
        return listResult(query);
    }

    public Item get(Object id) {
        EntityManager em = em();
        return em.find(Item.class, id);
    }
}