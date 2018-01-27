package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.ItemEO;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDAO<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    protected Provider<EntityManager> entityManagerProvider;

    protected EntityManager em() {
        return entityManagerProvider.get();
    }

    protected Collection<T> findAll(Class<ItemEO> clazz) {
        CriteriaBuilder cb = em().getCriteriaBuilder();
        CriteriaQuery<ItemEO> cq = cb.createQuery(clazz);
        Root<ItemEO> rootEntry = cq.from(clazz);
        CriteriaQuery<ItemEO> all = cq.select(rootEntry);
        return listResult(em().createQuery(all));
    }

    @SuppressWarnings("unchecked")
    protected T singleResult(Query query) {
        return (T) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    protected List<T> listResult(Query query) {
        return query.getResultList();
    }

    public void delete(T object) {
        em().remove(object);
    }

    public void persist(T entity) {
        EntityManager em = em();
        em.persist(entity);
    }

    public void merge(T entity) {
        em().merge(entity);
    }

    public abstract Collection<T> findAll();
}
