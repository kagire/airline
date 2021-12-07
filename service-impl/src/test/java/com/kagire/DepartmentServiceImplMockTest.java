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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DepartmentServiceImpl.class)
@ContextConfiguration(classes = MockTestConfig.class)
@ActiveProfiles("test")
class DepartmentServiceImplMockTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentService departmentService;

    @Test
    void findAllShouldReturnEmptyList() {
        Assertions.assertNotNull(departmentService.findAll());
    }

    @Test
    void findByIdShouldThrowDepartmentNotFoundException() {
        Assertions.assertThrows(DepartmentNotFoundException.class,() -> departmentService.findById((long)1));
   }

   @Test
   void departmentServicePageShouldReturnNull(){
        Assertions.assertNull(departmentService.findPaginated(new Pageable() {
            @Override
            public int getPageNumber() {return 0;}

            @Override
            public int getPageSize() {return 0;}

            @Override
            public long getOffset() {return 0;}

            @Override
            public Sort getSort() {return null;}

            @Override
            public Pageable next() {return null;}

            @Override
            public Pageable previousOrFirst() {return null;}

            @Override
            public Pageable first() {return null;}

            @Override
            public Pageable withPage(int i) {return null;}

            @Override
            public boolean hasPrevious() {return false;}
        }));
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