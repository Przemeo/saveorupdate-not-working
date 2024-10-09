package com.saveorupdate;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class ParentRepositoryTest {

    @Inject
    ParentRepository parentRepository;

    @Test
    void test1() {
        //given
        Parent parent = persistParent();

        //when
        saveOrUpdateParentWithNewChild(parent);

        //then
        assertTrue(parent.getOptionalId().isPresent());
        assertEquals("1", parent.getOptionalId().get());
    }

    private Parent persistParent() {
        Parent parent = new Parent();
        parent.setName("parent");
        Child child = new Child();
        child.setName("child1");
        parent.addChild(child);

        parentRepository.persist(parent);
        return parent;
    }

    private void saveOrUpdateParentWithNewChild(Parent parent) {
        //new child
        Child child2 = new Child();
        child2.setName("child2");
        parent.addChild(child2);

        parentRepository.saveOrUpdate(parent);
    }

    @Test
    void test2() {
        //given
        Parent parent = persistParent();

        //when
        saveOrUpdateParentWithNewChild();

        //then
        assertTrue(parent.getOptionalId().isPresent());
        assertEquals("1", parent.getOptionalId().get());
    }

    //does not work - child is not being inserted into db
    private void saveOrUpdateParentWithNewChild() {
        //simulate load from database
        Parent parent = new Parent();
        parent.setId("1");
        parent.setName("parent");
        parent.setVersion(0L);
        Child child1 = new Child();
        child1.setId("51");
        child1.setName("child1");
        //new child
        Child child2 = new Child();
        child2.setName("child2");
        parent.addChild(child1);
        parent.addChild(child2);

        parentRepository.saveOrUpdate(parent);
    }

}