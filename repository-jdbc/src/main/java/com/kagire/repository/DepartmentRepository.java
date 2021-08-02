package com.kagire.repository;

import com.kagire.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    @Query(value = "SELECT * FROM department ORDER BY id", nativeQuery = true)
    List<Department> findAll();

}
