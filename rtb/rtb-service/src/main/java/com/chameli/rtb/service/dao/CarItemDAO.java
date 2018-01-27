package com.chameli.rtb.service.dao;

import com.chameli.rtb.service.entity.CarItemEO;

import javax.persistence.Query;
import java.util.List;

public class CarItemDAO extends ItemDAO<CarItemEO> {

    public List<CarItemEO> findByMake(String make) {
        Query query = em().createNamedQuery("findByCarMake");
        query.setParameter("make", make);
        return listResult(query);
    }


}