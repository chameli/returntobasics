package com.chameli.rtb.interfaces.ejb;

import com.chameli.rtb.application.AdminItemService;
import com.chameli.rtb.domain.model.Item;
import com.chameli.rtb.interfaces.ItemAssembler;
import com.chameli.rtb.interfaces.ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Init;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class AdminItemFacadeBean implements AdminItemFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Inject
    private AdminItemService service;

    @PersistenceContext(unitName = "rtb")
    private EntityManager em;

    public AdminItemFacadeBean() {
        LOGGER.debug("Creating instance");
    }

    @Init
    public void init() {
        LOGGER.debug("Initializing");
    }

    @Override
    public ItemDTO findItem(long itemId) {
        LOGGER.debug("em {}", em);
        LOGGER.debug("Finding item with id {}", itemId);
        Item item = service.findItem(itemId);
        return ItemAssembler.toDTO(item);
    }

}
