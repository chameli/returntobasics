package com.chameli.rtb.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(generator = "store_seq")
    @SequenceGenerator(name = "store_seq", sequenceName = "SEQ_STORE", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Item> items;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public Store() {
        super();
    }

    public Store(String name) {
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

    public Collection<Item> getItems() {
        return items;
    }
}
