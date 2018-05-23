package com.chameli.rtb.domain.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import javax.persistence.*;

@Entity
@Table(name = "item")
@DiscriminatorColumn(name = "DTYPE")
@DiscriminatorValue("GENERIC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedQueries(@NamedQuery(name = "findById", query = "select i from Item i where i.id in :ids"))
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinFetch(value = JoinFetchType.INNER)
    private Store store;

    @Column(unique = true)
    private String name;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public Item() {
        // For JPAs sake
        super();
    }

    public Item(Store store, String name) {
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

    public Store getStore() {
        return store;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
