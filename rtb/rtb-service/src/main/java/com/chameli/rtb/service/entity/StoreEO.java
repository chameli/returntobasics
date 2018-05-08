package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "store")
public class StoreEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<ItemEO> items;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public StoreEO() {
        super();
    }

    public StoreEO(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).toString();
    }

    public Collection<ItemEO> getItems() {
        return items;
    }
}
