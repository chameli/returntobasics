package com.chameli.rtb.service;

import com.chameli.rtb.service.dao.ItemDAO;
import com.chameli.rtb.service.entity.CarItemEO;
import com.chameli.rtb.service.entity.ItemEO;
import com.google.inject.Inject;

public class AdminItemService {

    @Inject
    private ItemDAO dao;

    public ItemEO addCarItem(String make, String model) {
        ItemEO item = new CarItemEO(make, model);
        dao.persist(item);
        return item;
    }

}
