package com.chameli.rtb.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "food_item")
@DiscriminatorValue("FOOD")
public class FoodItem extends Item {

    public FoodItem() {
        super();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
