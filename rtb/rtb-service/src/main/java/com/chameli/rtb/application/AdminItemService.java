package com.chameli.rtb.application;

import com.chameli.rtb.domain.model.*;
import com.google.inject.Inject;

public class AdminItemService {

    @Inject
    private ItemRepository dao;

    @Inject
    private StoreRepository storeDao;

    public Item addCarItem(long storeId, String make, String model) {
        Store store = storeDao.get(storeId);
        Item item = new CarItem(store, make, model);
        dao.persist(item);
        return item;
    }

}
