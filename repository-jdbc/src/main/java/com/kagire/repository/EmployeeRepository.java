package com.kagire.repository;

import com.kagire.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee ORDER BY id", nativeQuery = true)
    List<Employee> findAll();

    @Query(value = "SELECT * FROM employee WHERE department_id = ?1 ORDER BY id", nativeQuery = true)
    List<Employee> findByDepartmentId(Integer departmentId);
}
