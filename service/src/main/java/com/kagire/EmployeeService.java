package com.kagire;

import com.kagire.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(Long employeeId);

    Long create(Employee employee);

    Long update(Employee employee);

    void delete(Long employeeId);

    Long count();
}
