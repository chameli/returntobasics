package com.chameli.rtb.domain.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public abstract class AbstractRepository {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager entityManager;

    protected EntityManager em() {
        return entityManager;
    }

    protected <Z> Collection<Z> findAll(Class<Z> clazz) {
        CriteriaBuilder cb = em().getCriteriaBuilder();
        CriteriaQuery<Z> cq = cb.createQuery(clazz);
        Root<Z> rootEntry = cq.from(clazz);
        CriteriaQuery<Z> all = cq.select(rootEntry);
        return listResult(em().createQuery(all));
    }

    @SuppressWarnings("unchecked")
    protected <Z> Z singleResult(Query query) {
        return (Z) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    protected <Z> List<Z> listResult(Query query) {
        return query.getResultList();
    }

    public void delete(Object object) {
        em().remove(object);
    }

    public void persist(Object entity) {
        EntityManager em = em();
        em.persist(entity);
    }

    public void merge(Object entity) {
        em().merge(entity);
    }
}
