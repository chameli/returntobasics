package com.chameli.rtb.service.dao;

import java.lang.reflect.ParameterizedType;
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

    private Class<T> persistentClazz;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.persistentClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public List<T> findAll() {
        List<T> resultList = em().createQuery(findAllStmt(), persistentClazz).getResultList();
        return resultList;
    }

    private String findAllStmt() {
        return "Select t From " + persistentClazz.getSimpleName() + " t";
    }

    protected EntityManager em() {
        return entityManagerProvider.get();
    }

    protected T internalGet(Object id) {
        EntityManager em = em();
        return em.find(persistentClazz, id);
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

}
