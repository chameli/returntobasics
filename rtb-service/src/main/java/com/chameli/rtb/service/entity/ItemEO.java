package com.chameli.rtb.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "item")
@NamedQueries(@NamedQuery(name = "findById", query = "select i from ItemEO i where i.id in :ids"))
public class ItemEO {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "ITEM_SEQUENCE", sequenceName = "item_sequence")
    @GeneratedValue(generator = "ITEM_SEQUENCE")
    private Long id;

    @Column(unique = true)
    private String name;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public ItemEO() {
        super();
    }

    public ItemEO(String name) {
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
