package com.chameli.rtb.application;

import com.chameli.rtb.domain.model.*;

import javax.inject.Inject;

public class AdminItemService {

    @Inject
    private StoreRepository storeRepository;

    @Inject
    private ItemRepository itemRepository;

    public Item addCarItem(long storeId, String make, String model) {
        Store store = storeRepository.get(storeId);
        Item item = new CarItem(store, make, model);
        itemRepository.persist(item);
        return item;
    }

    public Item findItem(long itemId) {
        return itemRepository.get(itemId);
    }

}
