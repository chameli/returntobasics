package com.chameli.rtb.service;

import com.chameli.rtb.service.dao.ItemDAO;
import com.chameli.rtb.service.entity.ItemEO;
import com.google.inject.Inject;

public class AdminItemService {

    @Inject
    private ItemDAO dao;

    public ItemEO addItem(String itemName) {
        ItemEO item = new ItemEO(itemName);
        dao.persist(item);
        return item;
    }

}
