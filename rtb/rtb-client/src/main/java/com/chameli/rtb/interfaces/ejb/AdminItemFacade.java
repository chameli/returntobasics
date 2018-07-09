package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.interfaces.ItemDTO;

public interface AdminItemFacade {

    String JNDI_NAME = "AdminItemFacade";

    ItemDTO findItem(long itemId);
}