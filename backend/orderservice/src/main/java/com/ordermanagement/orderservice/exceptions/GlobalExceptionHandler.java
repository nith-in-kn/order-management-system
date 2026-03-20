package com.ordermanagement.orderservice.exceptions;

import com.ordermanagement.orderservice.Models.dto.response.ErrorResponse;
import com.ordermanagement.orderservice.exceptions.custom.OrderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> OrderNotFoundException(
            OrderNotFoundException exception, HttpServletRequest request
    ){

        ErrorResponse responseBody = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> allOtherException(
            Exception exception, HttpServletRequest request
    ){

        ErrorResponse responseBody = new ErrorResponse(
                HttpStatus.EXPECTATION_FAILED.value(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(responseBody, HttpStatus.EXPECTATION_FAILED);
    }
}
