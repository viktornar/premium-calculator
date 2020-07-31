package com.github.viktornar.controllers;

import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.services.NoCustomerException;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
            NotFoundCalculatorException.class,
            NoCustomerException.class,
            NoCardException.class
    })
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponse(
                        ex.getMessage(),
                        HttpStatus.PARTIAL_CONTENT.value(),
                        new Timestamp((new Date()).getTime())
                ),
                new HttpHeaders(),
                HttpStatus.PARTIAL_CONTENT,
                request
        );
    }

    @Data
    static class ErrorResponse {
        private final String message;
        private final Integer code;
        private final Timestamp timestamp;
    }
}
