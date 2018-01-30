package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "item")
@DiscriminatorColumn(name = "DTYPE")
@DiscriminatorValue("GENERIC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(@NamedQuery(name = "findById", query = "select i from ItemEO i where i.id in :ids"))
public abstract class ItemEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private StoreEO store;

    @Column(unique = true)
    private String name;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public ItemEO() {
        // For JPAs sake
        super();
    }

    public ItemEO(StoreEO store, String name) {
        this.store = store;
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
        return ToStringBuilder.reflectionToString(this);
    }

}
