package com.chameli.rtb.interfaces;

import com.chameli.rtb.domain.model.CarItem;
import com.chameli.rtb.domain.model.Store;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class ItemAssemblerTest {

    @Test
    void carItemToDTO() {
        // Given
        CarItem item = createCarItem();

        // When
        ItemDTO dto = ItemAssembler.toDTO(item);

        // Then
        assertThat(dto.getId(), is(equalTo(item.getId())));
        assertThat(dto.getMake(), is(equalTo(item.getMake())));
    }

    private CarItem createCarItem() {
        CarItem item = new CarItem(createStore(), "Mazerati", "Biturbo");
        item.setId(17L);
        item.setHorsepowers(330);
        return item;
    }

    private Store createStore() {
        Store s = new Store("Store");
        s.setId(1717L);
        return s;
    }
}