package com.kagire;

import com.kagire.entity.Department;
import com.kagire.exceptions.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    public Optional<Department> findById(Long id) {
        if(departmentDao.findById(id).isPresent()) return departmentDao.findById(id);
        else throw new DepartmentNotFoundException(id);
    }

    public Long create(Department department) {
        return departmentDao.create(department);
    }

    public Long update(Department department) {
        return departmentDao.update(findById(department.getId()).get());
    }

    public void delete(Long departmentId) {
        departmentDao.delete(findById(departmentId).get().getId());
    }

    public Long count() {
        return departmentDao.count();
    }
}
