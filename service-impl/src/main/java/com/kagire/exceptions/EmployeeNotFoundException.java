package com.kagire.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(long employeeId) {
        super("Employee with id " + employeeId + " does not exist");
    }
}
