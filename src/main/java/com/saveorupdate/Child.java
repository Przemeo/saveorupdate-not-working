package com.saveorupdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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