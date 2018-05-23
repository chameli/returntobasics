package com.chameli.rtb.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "furniture_item")
@DiscriminatorValue("FURNITURE")
public class FurnitureItem extends Item {

    public FurnitureItem() {
        super();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
