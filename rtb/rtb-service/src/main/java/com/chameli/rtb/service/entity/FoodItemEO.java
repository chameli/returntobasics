package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food_item")
@DiscriminatorValue("FOOD")
public class FoodItemEO extends ItemEO {

    public FoodItemEO() {
        super();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
