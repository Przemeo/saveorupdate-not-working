package com.saveorupdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CHILD")
@Getter
@Setter
public class Child extends Id {

    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID", nullable = false)
    private Parent parent;

    @Column(name = "NAME", updatable = false, nullable = false)
    private String name;

}