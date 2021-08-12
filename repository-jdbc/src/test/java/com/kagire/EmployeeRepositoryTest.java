package com.kagire;

import com.kagire.config.TestConfig;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import com.kagire.repository.DepartmentRepository;
import com.kagire.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles(profiles = "test")
class EmployeeRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void findAllShouldReturnInitiatedEntities() {
        Assertions.assertFalse(employeeRepository.findAll().isEmpty());
    }

    @Test
    void findByIdShouldReturnInitiatedEntities() {
        Employee employee = employeeRepository.findAll().get(0);
        Assertions.assertEquals(Optional.of(employee), employeeRepository.findById(employee.getId()));
        employee = employeeRepository.findAll().get(1);
        Assertions.assertEquals(Optional.of(employee), employeeRepository.findById(employee.getId()));
    }

    @Test
    void createShouldAddNewEntity() {
        long size = employeeRepository.count();
        employeeRepository.save(new Employee("Three", new Date(3), 3, 3));
        Assertions.assertEquals(size + 1, employeeRepository.findAll().size());
    }

    @Test
    void deleteShouldDeleteAllEntitiesOneByOneInCycleAndDatabaseBeEmpty() {
        for(Employee employee : employeeRepository.findAll())
            employeeRepository.delete(employee);
        for(Department department : departmentRepository.findAll())
            departmentRepository.delete(department);
        Assertions.assertTrue(departmentRepository.findAll().isEmpty());
        Assertions.assertTrue(employeeRepository.findAll().isEmpty());
    }

    @Test
    void countShouldBeEqualsToTestInitiateSet() {
        Assertions.assertEquals(employeeRepository.findAll().size(), employeeRepository.count());
    }

    @Test
    void findByDepartmentIdShouldReturnRelated(){
        Department department = departmentRepository.save(new Department("WHAT"));
        Employee employee = employeeRepository.save(new Employee("Three", new Date(3), 3, department.getId()));
        Assertions.assertEquals(1, employeeRepository.findByDepartmentId((int)department.getId()).size());
    }
}