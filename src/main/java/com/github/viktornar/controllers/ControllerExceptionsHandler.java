package com.github.viktornar.controllers;

import com.github.viktornar.premium.calculator.NotFoundCalculatorException;
import com.github.viktornar.services.NoCardException;
import com.github.viktornar.services.NoCustomerException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionsHandler {
    @ExceptionHandler(value = {
            NotFoundCalculatorException.class,
            NoCustomerException.class,
            NoCardException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ErrorResponse handleValidation(Exception ex, WebRequest request) {
        return new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                new Timestamp((new Date()).getTime())
        );
    }

    @Data
    static class ErrorResponse {
        private final String message;
        private final String error;
        private final Integer code;
        private final Timestamp timestamp;
    }
}
