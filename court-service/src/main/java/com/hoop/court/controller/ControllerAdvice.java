package com.hoop.court.controller;

import com.hoop.court.dto.response.ErrorResponse;
import com.hoop.court.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandle(RuntimeException ex){
        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage()).code("500-Internal Server Error").build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<ErrorResponse> requestExceptionHandle(RequestException ex){
        ErrorResponse errorResponse = ErrorResponse.builder().message(ex.getMessage()).code(ex.getCode()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
