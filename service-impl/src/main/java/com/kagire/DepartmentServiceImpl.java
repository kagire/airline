package com.kagire;

import com.kagire.entity.Department;
import com.kagire.exceptions.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        if(departmentDao.findById(id).isPresent()) return departmentDao.findById(id);
        else throw new DepartmentNotFoundException(id);
    }

    @Override
    public Long create(Department department) {
        return departmentDao.create(department);
    }

    @Override
    public Long update(Department department) {
        findById(department.getId());
        return departmentDao.update(department);
    }

    @Override
    public void delete(Long departmentId) {
        departmentDao.delete(findById(departmentId).get().getId());
    }

    @Override
    public Long count() {
        return departmentDao.count();
    }
}
