package com.app.demoprojection.errors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class DocumentNotValidException extends ConstraintViolationException {
    public DocumentNotValidException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}
