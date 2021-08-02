package com.kagire;

import com.kagire.entity.Employee;
import com.kagire.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public Optional<Employee> findById(Long id) {
        if(employeeDao.findById(id).isPresent()) return employeeDao.findById(id);
        else throw new EmployeeNotFoundException(id);
    }

    public Long create(Employee employee) {
        return employeeDao.create(employee);
    }

    public Long update(Employee employee) {
        return employeeDao.update(findById(employee.getId()).get());
    }

    public void delete(Long employeeId) {
        employeeDao.delete(findById(employeeId).get().getId());
    }

    public Long count() {
        return employeeDao.count();
    }
}
