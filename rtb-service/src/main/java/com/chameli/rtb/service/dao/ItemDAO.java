package com.chameli.rtb.service.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import com.chameli.rtb.service.entity.ItemEO;

public class ItemDAO extends AbstractDAO<ItemEO> {

    public List<ItemEO> get(Long... ids) {
        Query query = em().createNamedQuery("findById");
        query.setParameter("ids", Arrays.asList(ids));
        return listResult(query);
    }

}