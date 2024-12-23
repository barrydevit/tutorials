package com.app.demoprojection.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    private final Logger log = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String field =  ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erreur lors de la validation", errors), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DocumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNotValidException(DocumentNotValidException exception) {
        log.error(exception.toString());
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().forEach((constraintViolation) -> {
            String field = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessageTemplate();
            errors.put(field, message);
        });
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Erreur lors de la validation", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.error(exception.toString());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), errors), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error(exception.toString());
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Erreur inattendue du server");
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur inattendue du server", errors), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
