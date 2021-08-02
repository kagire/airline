package com.kagire.entity;

import org.assertj.core.error.ShouldHaveSameHashCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    public void getDepartmentSetters() {
        Department department = new Department("Two");

        Assertions.assertEquals("Two", department.getName());

        department.setId(1);
        department.setName("One");

        Assertions.assertEquals(1, department.getId());
        Assertions.assertEquals("One", department.getName());
    }

    @Test
    public void equalsTest(){
        Department department = new Department("");
        assert department.equals(department);
        Assertions.assertFalse(department.equals(new Object()));
        Department department1 = new Department("");
        Assertions.assertTrue(department.equals(department1));

    }

    @Test
    public void toStringTest(){
        Assertions.assertEquals("Department{" + "id=" + 0 + ", name='" + '\'' + '}', new Department("").toString());
    }

    @Test
    public void cloneDataTest(){
        Department department = new Department("");
        Department department1 = new Department("1111");
        department.cloneData(department1);
        Assertions.assertEquals(department, department1);

        Assertions.assertEquals(department.hashCode(), department1.hashCode());
    }
}