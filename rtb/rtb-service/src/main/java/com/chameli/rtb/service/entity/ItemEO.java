package com.chameli.rtb.service.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "item")
@DiscriminatorColumn(name="DTYPE")
@DiscriminatorValue("GENERIC")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries(@NamedQuery(name = "findById", query = "select i from ItemEO i where i.id in :ids"))
public class ItemEO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
