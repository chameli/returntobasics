package com.chameli.rtb.service.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;

public abstract class AbstractDAO<T> {
    protected final Logger logger = Logger.getLogger(getClass());

    @Inject
    protected Provider<EntityManager> entityManagerProvider;

    public List<T> findAll() {
        List<T> resultList = em().createQuery(findAllStmt(), getPersistentClazz()).getResultList();
        return resultList;
    }

    private String findAllStmt() {
        return "Select t From " + getPersistentClazz().getSimpleName() + " t";
    }

    protected EntityManager em() {
        return entityManagerProvider.get();
    }

    protected T internalGet(Object id) {
        EntityManager em = em();
        return em.find(getPersistentClazz(), id);
    }

    @SuppressWarnings("unchecked")
    protected T singleResult(Query query) {
        return (T) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    protected List<T> listResult(Query query) {
        return query.getResultList();
    }

    public T get(Object id) {
        return internalGet(id);
    }

    public void delete(long id) {
        em().remove(internalGet(id));
    }

    public void persist(T entity) {
        EntityManager em = em();
        em.persist(entity);
    }

    public void merge(T entity) {
        em().merge(entity);
    };

    protected abstract Class<T> getPersistentClazz();

}
