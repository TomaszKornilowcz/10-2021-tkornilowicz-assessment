package com.tkornilowicz.assessment.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id) {
        super("Cannot find department with id: "  + id);

    }
}
