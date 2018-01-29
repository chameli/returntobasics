package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.StoreEO;

import javax.persistence.EntityManager;

public class StoreDAO extends AbstractDAO<StoreEO> {

    public StoreEO get(Object id) {
        EntityManager em = em();
        return em.find(StoreEO.class, id);
    }
}