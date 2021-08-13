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
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DepartmentDaoJdbc.class, EmployeeDaoJdbc.class})
@ActiveProfiles("test")
class MockDaoJdbcTest {

    @MockBean
    DepartmentRepository departmentRepository;

    @MockBean
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentDao departmentDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void findAllShouldBeEmpty() {
        Assertions.assertTrue(departmentDao.findAll().isEmpty());
        Assertions.assertTrue(employeeDao.findAll().isEmpty());
    }

    @Test
    void findByIdShouldBeEmpty() {
        Assertions.assertFalse(departmentDao.findById((long)0).isPresent());
        Assertions.assertFalse(employeeDao.findById((long)0).isPresent());
    }

    @Test
    void createShouldReturnIdZero() {
        when(departmentRepository.save(any())).thenReturn(new Department("a"));
        when(employeeRepository.save(any())).thenReturn(new Employee("a", new Date(1), 1, 1));

        Assertions.assertEquals(0, departmentDao.create(any()));
        Assertions.assertEquals(0, employeeDao.create(any()));
    }

    @Test
    void updateShouldReturnAValue() {
        when(departmentRepository.save(any())).thenReturn(new Department(""));
        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        when(employeeRepository.save(any())).thenReturn(new Employee("a", new Date(1), 1, 1));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("a", new Date(1), 1, 1)));

        Assertions.assertNotNull(departmentDao.update(new Department("")));
        Assertions.assertNotNull(employeeDao.update(new Employee("a", new Date(1), 1, 1)));
    }

    @Test
    void deleteShouldExecute() {
        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("a", new Date(1), 1, 1)));

        Assertions.assertDoesNotThrow(() -> departmentDao.delete((long)1));
        Assertions.assertDoesNotThrow(() -> employeeDao.delete((long)1));
    }

    @Test
    void countShouldNotBeNull() {
        Assertions.assertNotNull(departmentDao.count());
        Assertions.assertNotNull(employeeDao.count());
    }

    @Test
    void findByDepartmentIdShould(){
        Assertions.assertNotNull(employeeDao.findByDepartmentId(1));
    }
}