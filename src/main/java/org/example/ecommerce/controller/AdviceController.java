package org.example.ecommerce.controller;

import org.example.ecommerce.exception.ApplicationErrorsExceptions;
import org.example.ecommerce.exception.BusinessRuleException;
import org.example.ecommerce.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationErrorsExceptions handleBusiness(BusinessRuleException ex) {
        return new ApplicationErrorsExceptions(ex.getMessage());
    }
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApplicationErrorsExceptions handleOrderNotFound(OrderNotFoundException ex) {
        return new ApplicationErrorsExceptions(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApplicationErrorsExceptions handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApplicationErrorsExceptions(errors);
    }
}

