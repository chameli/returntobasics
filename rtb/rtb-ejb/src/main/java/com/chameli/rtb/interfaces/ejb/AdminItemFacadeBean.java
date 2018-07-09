package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.application.AdminItemService;
import com.chameli.rtb.domain.model.Item;
import com.chameli.rtb.interfaces.ItemDTO;

import javax.ejb.Stateless;

@Stateless
public class AdminItemFacadeBean extends AbstractFacadeBean implements AdminItemFacade {

    @Override
    public ItemDTO addCarItem(long storeId, String make, String model) {
        Item item = getService(AdminItemService.class).addCarItem(storeId, make, model);
        return ItemAssembler.toDTO(item);
    }

}
