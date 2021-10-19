package com.tkornilowicz.assessment.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Cannot find employee with id: "  + id);
    }
}
