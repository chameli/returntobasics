package com.chameli.rtb.interfaces;

import java.io.Serializable;

public class ItemDTO implements Serializable {

    private long id;

    private long storeId;

    private String name;

    private long version;

    private int horsepowers;

    private String make;

    private String model;

    public long getId() {
        return id;
    }

    public long getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public long getVersion() {
        return version;
    }

    public int getHorsepowers() {
        return horsepowers;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setHorsepowers(int horsepowers) {
        this.horsepowers = horsepowers;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
