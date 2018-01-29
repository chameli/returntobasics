package com.chameli.rtb.ejb;

import com.chameli.rtb.service.entity.ItemEO;

import javax.ejb.Local;

@Local
public interface AdminItemFacade {

    String JNDI_NAME = "AdminItemFacade";

    ItemEO addCarItem(long storeId, String make, String model);
}