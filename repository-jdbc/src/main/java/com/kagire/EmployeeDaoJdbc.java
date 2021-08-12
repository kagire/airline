package com.kagire;

import com.kagire.entity.Employee;
import com.kagire.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoJdbc implements EmployeeDao{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Long create(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    @Override
    public Long update(Employee employee) {
        Employee oldEmployee = employeeRepository.findById(employee.getId()).get();
        oldEmployee.cloneData(employee);
        return employeeRepository.save(oldEmployee).getId();
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Long count() {
        return employeeRepository.count();
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
