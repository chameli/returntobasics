package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.domain.model.Item;

import javax.ejb.Local;

@Local
public interface AdminItemFacade {

    String JNDI_NAME = "AdminItemFacade";

    Item addCarItem(long storeId, String make, String model);
}