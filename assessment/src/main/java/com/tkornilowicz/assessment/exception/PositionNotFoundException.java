package com.tkornilowicz.assessment.exception;

public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException(Long id) {
        super("Cannot find position with id: "  + id);
    }
}
