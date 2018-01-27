package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "car_item")
@DiscriminatorValue("CAR")
@NamedQueries(@NamedQuery(name = "findByCarMake", query = "select c from CarItemEO c where c.make = :make"))
public class CarItemEO extends ItemEO {

    private int horsepowers;

    private String make;

    private String model;

    public CarItemEO() {
        super();
    }

    public CarItemEO(String make, String model) {
        super(make + model);
        this.make = make;
        this.model = model;
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
