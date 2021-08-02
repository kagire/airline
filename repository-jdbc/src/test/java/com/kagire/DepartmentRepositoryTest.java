package com.kagire;

import com.kagire.config.TestConfig;
import com.kagire.entity.Department;
import com.kagire.repository.DepartmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes = {DbConfig.class, TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class DepartmentRepositoryTest {

    Logger logger = LoggerFactory.getLogger(DepartmentDaoJdbc.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void initiateBeforeEachTest(){
        entityManager.persist(new Department("One"));
        departmentRepository.save(new Department("Two"));
    }

    @Test
    void findAllShouldReturnInitiatedEntities() {
        Assertions.assertEquals(2, departmentRepository.findAll().size());
        for(Department department : departmentRepository.findAll())
            logger.info(department.toString());
    }

    @Test
    void findByIdShouldReturnInitiatedEntities() {
        Department department = departmentRepository.findAll().get(0);
        Assertions.assertEquals(Optional.of(department), departmentRepository.findById(department.getId()));
        department = departmentRepository.findAll().get(1);
        Assertions.assertEquals(Optional.of(department), departmentRepository.findById(department.getId()));
    }

    @Test
    void createShouldAddNewEntity() {
        departmentRepository.save(new Department("Three"));
        Assertions.assertEquals(3, departmentRepository.findAll().size());
    }

    @Test
    void deleteShouldDeleteAllEntitiesOneByOneInCycle() {
        for(Department department : departmentRepository.findAll())
            departmentRepository.delete(department);
        Assertions.assertTrue(departmentRepository.findAll().isEmpty());
    }

    @Test
    void countShouldBeEqualsToTestInitiateSet() {
        Assertions.assertEquals(2, departmentRepository.count());
    }
}