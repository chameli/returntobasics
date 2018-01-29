package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "furniture_item")
@DiscriminatorValue("FURNITURE")
public class FurnitureItemEO extends ItemEO {

    public FurnitureItemEO() {
        super();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
