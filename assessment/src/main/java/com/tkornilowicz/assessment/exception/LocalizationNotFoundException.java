package com.tkornilowicz.assessment.exception;

public class LocalizationNotFoundException extends RuntimeException {
    public LocalizationNotFoundException(Long id) {
        super("Cannot find localization with id: "  + id);
    }
}
