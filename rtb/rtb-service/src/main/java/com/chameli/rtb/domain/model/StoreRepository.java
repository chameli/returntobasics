package com.chameli.rtb.domain.model;

import javax.persistence.EntityManager;

public class StoreRepository extends AbstractRepository<Store> {

    public Store get(Object id) {
        EntityManager em = em();
        return em.find(Store.class, id);
    }
}