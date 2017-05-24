package com.chameli.rtb.service.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "car_item")
public class CarItemEO extends ItemEO {

    private int horsepowers;

    public CarItemEO() {
        super();
    }

    public CarItemEO(String make, String model) {
        super(make + model);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public int getHorsepowers() {
        return horsepowers;
    }

    public void setHorsepowers(int horsepowers) {
        this.horsepowers = horsepowers;
    }

}
