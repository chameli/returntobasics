package com.chameli.rtb.application;

import com.chameli.rtb.domain.model.*;

import javax.inject.Inject;

public class AdminItemService {

    @Inject
    private ItemRepository dao;

    @Inject
    private StoreRepository storeDao;

    @Inject
    private ItemRepository itemDao;

    public Item addCarItem(long storeId, String make, String model) {
        Store store = storeDao.get(storeId);
        Item item = new CarItem(store, make, model);
        dao.persist(item);
        return item;
    }

    public Item findItem(long itemId) {
        return itemDao.get(itemId);
    }

}
