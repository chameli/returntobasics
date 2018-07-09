package com.chameli.rtb.interfaces;

import com.chameli.rtb.domain.model.Item;
import com.chameli.rtb.domain.model.Store;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class ItemAssembler {
    public static ItemDTO toDTO(Item item) {
        return map(item);
    }

    public static StoreDTO toDTO(Store item) {
        return mapper().map(item, StoreDTO.class);
    }

    private static ItemDTO map(Item source) {
        return mapper().map(source, ItemDTO.class);
    }

    private static MapperFacade mapper() {
        // TODO Make singleton using CDI
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Item.class, ItemDTO.class).byDefault();
        mapperFactory.classMap(Store.class, StoreDTO.class).byDefault();
        return mapperFactory.getMapperFacade();
    }
}
