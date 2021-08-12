package com.kagire;

import com.kagire.entity.Employee;
import com.kagire.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDao employeeDao;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        if(employeeDao.findById(id).isPresent()) return employeeDao.findById(id);
        else throw new EmployeeNotFoundException(id);
    }

    @Override
    public Long create(Employee employee) {
        departmentService.findById(employee.getDepartmentId());
        return employeeDao.create(employee);
    }

    @Override
    public Long update(Employee employee) {
        findById(employee.getId());
        return employeeDao.update(employee);
    }

    @Override
    public void delete(Long employeeId) {
        employeeDao.delete(findById(employeeId).get().getId());
    }

    @Override
    public Long count() {
        return employeeDao.count();
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        return employeeDao.findByDepartmentId(departmentId);
    }
}
