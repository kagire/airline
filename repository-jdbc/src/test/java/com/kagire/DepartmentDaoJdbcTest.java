package com.kagire;

import com.kagire.config.TestConfig;
import com.kagire.entity.Department;
import com.kagire.repository.DepartmentRepository;
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

@DataJpaTest
@ContextConfiguration(classes = {DbConfig.class, TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class DepartmentDaoJdbcTest {

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
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void count() {
    }
}