package com.saveorupdate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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