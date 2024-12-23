package com.app.demoprojection.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PhoneValidation.class)
@Target({ElementType.METHOD , ElementType.FIELD , ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "Le numero doit etre de type orange sn";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
