package com.kagire;

import com.kagire.config.TestConfig;
import com.kagire.entity.Department;
import com.kagire.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ActiveProfiles(profiles = "test")
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void findAllShouldReturnInitiatedEntities() {
        Assertions.assertFalse(departmentRepository.findAll().isEmpty());
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
        long size = departmentRepository.count();
        departmentRepository.save(new Department("WHAT"));
        Assertions.assertEquals(size + 1, departmentRepository.findAll().size());
    }

    @Test
    void countShouldBeEqualsToTestInitiateSet() {
        Assertions.assertEquals(departmentRepository.findAll().size(), departmentRepository.count());
    }
}