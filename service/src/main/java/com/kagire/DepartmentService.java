package com.kagire;

import com.kagire.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAll();

    Page<Department> findPaginated(Pageable pageable);

    Optional<Department> findById(Long departmentId);

    Long create(Department department);

    Long update(Department department);

    void delete(Long departmentId);

    Long count();
}
