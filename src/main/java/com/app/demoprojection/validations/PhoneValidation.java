package com.app.demoprojection.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidation implements ConstraintValidator<Phone, String> {
    private final Pattern pattern = Pattern.compile("^(77|78)\\d{7}$");
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(pattern.pattern());
    }
}
