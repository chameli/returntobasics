package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_item")
@DiscriminatorValue("CAR")
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
