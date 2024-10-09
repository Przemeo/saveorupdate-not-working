package com.saveorupdate;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.util.Optional;

@MappedSuperclass
@Getter(AccessLevel.PRIVATE)
@Setter
public class Id {

    private static final String ID_GENERATOR_STRATEGY_NAME = "ID_GENERATOR";

    @jakarta.persistence.Id
    @GenericGenerator(name = ID_GENERATOR_STRATEGY_NAME,
                      type = SequenceStringIdGenerator.class,
                      parameters = {
                              @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "MAIN_SEQUENCE"),
                              @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "50")
                      })
    @GeneratedValue(generator = ID_GENERATOR_STRATEGY_NAME)
    @Column(name = "ID", nullable = false, updatable = false, insertable = false)
    @Access(AccessType.PROPERTY)
    public String id;

    public Optional<String> getOptionalId() {
        return Optional.ofNullable(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Id other = (Id) o;
        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}