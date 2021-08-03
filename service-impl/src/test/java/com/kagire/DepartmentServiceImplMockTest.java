package com.kagire;

import com.kagire.config.MockTestConfig;
import com.kagire.entity.Department;
import com.kagire.exceptions.DepartmentNotFoundException;
import com.kagire.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DepartmentServiceImpl.class)
@ContextConfiguration(classes = MockTestConfig.class)
class DepartmentServiceImplMockTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentServiceImpl departmentService;

    @Test
    void findAllShouldReturnEmptyList() {
        Assertions.assertNotNull(departmentService.findAll());
    }

    @Test
    void findByIdShouldThrowDepartmentNotFoundException() {
        Assertions.assertThrows(DepartmentNotFoundException.class,() -> departmentService.findById((long)1));
   }

    @Test
    void createShouldProcessButEntityListEmpty() {
        when(departmentRepository.save(any())).thenReturn(new Department("a"));

        Assertions.assertNotNull(departmentService.create(Mockito.any()));
        Assertions.assertThrows(DepartmentNotFoundException.class,() -> departmentService.findById((long)0));
    }

    @Test
    void updateShouldThrowDepartmentNotFoundExceptionButNotWhenMocked() {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentService.update(new Department("Soulmate")));

        when(departmentRepository.save(any())).thenReturn(new Department(""));
        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        Assertions.assertNotNull(departmentService.update(new Department("")));
    }

    @Test
    void deleteShouldThrowDepartmentNotFoundExceptionButNotWhenMocked() {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentService.delete(Mockito.anyLong()));

        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        Assertions.assertDoesNotThrow(() -> departmentService.delete((long)1));
    }

    @Test
    void countShouldReturnNonNullValue() {
        Assertions.assertNotNull(departmentService.count());
    }
}