package com.chameli.rtb.domain.model;

import javax.persistence.Query;
import java.util.List;

public class CarItemRepository extends ItemRepository<CarItem> {

    public List<CarItem> findByMake(String make) {
        Query query = em().createNamedQuery("findByCarMake");
        query.setParameter("make", make);
        return listResult(query);
    }


}