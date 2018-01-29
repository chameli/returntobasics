package com.chameli.rtb.service;

import com.chameli.rtb.service.dao.ItemDAO;
import com.chameli.rtb.service.dao.StoreDAO;
import com.chameli.rtb.service.entity.CarItemEO;
import com.chameli.rtb.service.entity.ItemEO;
import com.chameli.rtb.service.entity.StoreEO;
import com.google.inject.Inject;

public class AdminItemService {

    @Inject
    private ItemDAO dao;

    @Inject
    private StoreDAO storeDao;

    public ItemEO addCarItem(long storeId, String make, String model) {
        StoreEO store = storeDao.get(storeId);
        ItemEO item = new CarItemEO(store, make, model);
        dao.persist(item);
        return item;
    }

}
