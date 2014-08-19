package com.chameli.rtb.ejb;

import javax.ejb.Local;

import com.chameli.rtb.service.entity.ItemEO;

@Local
public interface AdminItemFacade {

    String JNDI_NAME = "AdminItemFacade";

    ItemEO addItem(String itemName);
}