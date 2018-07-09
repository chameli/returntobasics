package com.chameli.rtb.interfaces;

import java.io.Serializable;

public class StoreDTO implements Serializable {
    private long id;

    private String name;

    private long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
