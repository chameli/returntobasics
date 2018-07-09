package com.chameli.rtb.rs.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/item")
public class ItemAPI {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    @GET
    @Path("/find/{id}")
    @Produces("text/plain")
    public String getItem(@PathParam("id") long id) {
        LOGGER.debug("Getting item with id {}", id);
        return "Item " + id;
    }

}
