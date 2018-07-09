package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.application.AdminItemService;
import com.chameli.rtb.domain.model.Item;
import com.chameli.rtb.interfaces.ItemAssembler;
import com.chameli.rtb.interfaces.ItemDTO;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class AdminItemFacadeBean extends AbstractFacadeBean implements AdminItemFacade {

    @Override
    public ItemDTO findItem(long itemId) {
        Item item = getService(AdminItemService.class).findItem(itemId);
        return ItemAssembler.toDTO(item);
    }


}
