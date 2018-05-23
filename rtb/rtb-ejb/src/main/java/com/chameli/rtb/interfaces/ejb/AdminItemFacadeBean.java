package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.application.AdminItemService;
import com.chameli.rtb.domain.model.Item;

import javax.ejb.Stateless;

@Stateless
public class AdminItemFacadeBean extends AbstractFacadeBean implements AdminItemFacade {

    @Override
    public Item addCarItem(long storeId, String make, String model) {
        return getService(AdminItemService.class).addCarItem(storeId, make, model);
    }

}
