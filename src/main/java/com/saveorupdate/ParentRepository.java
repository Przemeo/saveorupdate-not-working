package com.saveorupdate;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;

@ApplicationScoped
public class ParentRepository {

    private final EntityManager entityManager;

    ParentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persist(Parent parent) {
        entityManager.persist(parent);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void saveOrUpdate(Parent parent) {
        Session session = entityManager.unwrap(Session.class);
        session.clear();
        session.saveOrUpdate(parent);
    }

}