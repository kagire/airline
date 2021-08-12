package com.kagire.controllers;

import com.kagire.EmployeeService;
import com.kagire.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("employees")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> employees() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        Optional<Employee> optional = employeeService.findById(id);
        return optional.isPresent() ? new ResponseEntity<>(optional.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Long> newEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Long> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEmployees() {
        return new ResponseEntity<>(employeeService.count(), HttpStatus.OK);
    }

    @GetMapping("/dedicated/{departmentId}")
    public ResponseEntity<List<Employee>> dedicatedEmployees(@PathVariable int departmentId) {
        return new ResponseEntity<>(employeeService.findByDepartmentId(departmentId), HttpStatus.OK);
    }
}
