package com.chameli.rtb.ejb;

import com.chameli.rtb.service.AdminItemService;
import com.chameli.rtb.service.entity.ItemEO;

import javax.ejb.Stateless;

@Stateless
public class AdminItemFacadeBean extends AbstractFacadeBean implements AdminItemFacade {

    @Override
    public ItemEO addCarItem(String make, String model) {
        return getService(AdminItemService.class).addCarItem(make, model);
    }

}
