package com.chameli.rtb.rs.api;

import com.chameli.rtb.interfaces.ItemDTO;
import com.chameli.rtb.interfaces.ejb.AdminItemFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/item")
public class ItemAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @EJB
    private AdminItemFacade adminItemFacade;

    @GET
    @Path("/find/{itemId}")
    @Produces("text/json")
    public ItemDTO getItem(@PathParam("itemId") long itemId) {
        LOGGER.debug("Getting item with itemId {}", itemId);
        return adminItemFacade.findItem(itemId);
    }

}
