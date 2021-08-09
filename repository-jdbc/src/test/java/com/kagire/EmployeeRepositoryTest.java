package com.kagire;

import com.kagire.config.TestConfig;
import com.kagire.entity.Employee;
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
@ContextConfiguration(classes = {DbConfig.class, TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles(profiles = "test")
class EmployeeRepositoryTest {

    Logger logger = LoggerFactory.getLogger(EmployeeDaoJdbc.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void initiateBeforeEachTest(){
        entityManager.persist(new Employee("One", new Date(1), 1, 1));
        employeeRepository.save(new Employee("Two", new Date(2), 2, 2));
    }

    @Test
    void findAllShouldReturnInitiatedEntities() {
        Assertions.assertEquals(2, employeeRepository.findAll().size());
        for(Employee employee : employeeRepository.findAll())
            logger.info(employee.toString());
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
        employeeRepository.save(new Employee("Three", new Date(3), 3, 3));
        Assertions.assertEquals(3, employeeRepository.findAll().size());
    }

    @Test
    void deleteShouldDeleteAllEntitiesOneByOneInCycle() {
        for(Employee employee : employeeRepository.findAll())
            employeeRepository.delete(employee);
        Assertions.assertTrue(employeeRepository.findAll().isEmpty());
    }

    @Test
    void countShouldBeEqualsToTestInitiateSet() {
        Assertions.assertEquals(2, employeeRepository.count());
    }
}