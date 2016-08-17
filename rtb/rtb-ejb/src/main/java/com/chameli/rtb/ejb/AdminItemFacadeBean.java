package com.chameli.rtb.ejb;

import javax.ejb.Stateless;

import com.chameli.rtb.service.AdminItemService;
import com.chameli.rtb.service.entity.ItemEO;

@Stateless
public class AdminItemFacadeBean extends AbstractFacadeBean implements AdminItemFacade {

    @Override
    public ItemEO addItem(String itemName) {
        return getService(AdminItemService.class).addItem(itemName);
    }

}
