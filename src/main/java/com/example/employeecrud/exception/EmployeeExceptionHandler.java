package com.example.employeecrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException e){
        EmployeeException employeeException=new EmployeeException(
                e.getMessage(),
                e.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(employeeException, HttpStatus.NOT_FOUND);
    }
}
