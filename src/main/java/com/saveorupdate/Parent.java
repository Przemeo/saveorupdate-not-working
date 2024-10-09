package com.saveorupdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PARENT")
@Getter
@Setter
public class Parent extends Id {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Version
    @Column(name = "VERSION_COUNTER")
    private long version;

    @OneToMany(mappedBy = Child_.PARENT)
    @Cascade({CascadeType.PERSIST, CascadeType.MERGE, CascadeType.SAVE_UPDATE})
    private Set<Child> children = new LinkedHashSet<>();

    public void addChild(Child child) {
        children.add(child);
        child.setParent(this);
    }

}