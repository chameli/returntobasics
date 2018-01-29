package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.ItemEO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ItemDAO<T extends ItemEO> extends AbstractDAO<T> {

    public List<T> findById(Long... ids) {
        Query query = em().createNamedQuery("findById");
        query.setParameter("ids", Arrays.asList(ids));
        return listResult(query);
    }

    public ItemEO get(Object id) {
        EntityManager em = em();
        return em.find(ItemEO.class, id);
    }
}