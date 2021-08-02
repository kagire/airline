package com.kagire;

import com.kagire.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentDao {

    List<Department> findAll();

    Optional<Department> findById(Long departmentId);

    Long create(Department department);

    Long update(Department department);

    void delete(Long departmentId);

    Long count();
}
