package com.kagire;

import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import com.kagire.repository.DepartmentRepository;
import com.kagire.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DepartmentDaoJdbc.class, EmployeeDaoJdbc.class})
class MockDaoJdbcTest {

    @MockBean
    DepartmentRepository departmentRepository;

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentDaoJdbc departmentDaoJdbc;

    @Autowired
    EmployeeDaoJdbc employeeDaoJdbc;

    @Test
    void findAllShouldBeEmpty() {
        Assertions.assertTrue(departmentDaoJdbc.findAll().isEmpty());
        Assertions.assertTrue(employeeDaoJdbc.findAll().isEmpty());
    }

    @Test
    void findByIdShouldBeEmpty() {
        Assertions.assertFalse(departmentDaoJdbc.findById((long)0).isPresent());
        Assertions.assertFalse(employeeDaoJdbc.findById((long)0).isPresent());
    }

    @Test
    void createShouldReturnIdZero() {
        when(departmentRepository.save(any())).thenReturn(new Department("a"));
        when(employeeRepository.save(any())).thenReturn(new Employee("a", new Date(1), 1, 1));

        Assertions.assertEquals(0, departmentDaoJdbc.create(any()));
        Assertions.assertEquals(0, employeeDaoJdbc.create(any()));
    }

    @Test
    void updateShouldReturnAValue() {
        when(departmentRepository.save(any())).thenReturn(new Department(""));
        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        when(employeeRepository.save(any())).thenReturn(new Employee("a", new Date(1), 1, 1));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("a", new Date(1), 1, 1)));

        Assertions.assertNotNull(departmentDaoJdbc.update(new Department("")));
        Assertions.assertNotNull(employeeDaoJdbc.update(new Employee("a", new Date(1), 1, 1)));
    }

    @Test
    void deleteShouldExecute() {
        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("a", new Date(1), 1, 1)));

        Assertions.assertDoesNotThrow(() -> departmentDaoJdbc.delete((long)1));
        Assertions.assertDoesNotThrow(() -> employeeDaoJdbc.delete((long)1));
    }

    @Test
    void countShouldNotBeNull() {
        Assertions.assertNotNull(departmentDaoJdbc.count());
        Assertions.assertNotNull(employeeDaoJdbc.count());
    }
}