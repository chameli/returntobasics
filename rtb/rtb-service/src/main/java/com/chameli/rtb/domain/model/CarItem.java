package com.chameli.rtb.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "car_item")
@DiscriminatorValue("CAR")
@NamedQueries(@NamedQuery(name = "findByCarMake", query = "select c from CarItem c where c.make = :make"))
public class CarItem extends Item {

    private int horsepowers;

    private String make;

    private String model;

    public CarItem() {
        super();
    }

    public CarItem(Store store, String make, String model) {
        super(store, make + model);
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

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }
}
