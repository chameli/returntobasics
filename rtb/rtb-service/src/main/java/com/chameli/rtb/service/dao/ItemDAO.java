package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.ItemEO;

import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;

public class ItemDAO extends AbstractDAO<ItemEO> {

    public List<ItemEO> get(Long... ids) {
        Query query = em().createNamedQuery("findById");
        query.setParameter("ids", Arrays.asList(ids));
        return listResult(query);
    }

    @Override
    protected Class<ItemEO> getPersistentClazz() {
        return ItemEO.class;
    }

}