package com.kagire.exceptions;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(long departmentId) {
        super("Department with id " + departmentId + " does not exist");
    }
}
