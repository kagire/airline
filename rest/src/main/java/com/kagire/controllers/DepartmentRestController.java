package com.kagire.controllers;

import com.kagire.DepartmentService;
import com.kagire.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("departments")
public class DepartmentRestController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> departments() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable long id) {
        Optional<Department> optional = departmentService.findById(id);
        return optional.isPresent() ? new ResponseEntity<>(optional.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Long> newDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Long> updateDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.update(department), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable long id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countDepartments() {
        return new ResponseEntity<>(departmentService.count(), HttpStatus.OK);
    }
}
