package com.kagire;

import com.kagire.entity.Department;
import com.kagire.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDaoJdbc implements DepartmentDao{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public Long create(Department department) {
        return departmentRepository.save(department).getId();
    }

    @Override
    public Long update(Department department) {
        Department oldDepartment = departmentRepository.findById(department.getId()).get();
        oldDepartment.cloneData(department);
        return departmentRepository.save(oldDepartment).getId();
    }

    @Override
    public void delete(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Long count() {
        return departmentRepository.count();
    }
}
