package com.hoop.court.controller;

import com.hoop.court.error.Error;
import com.hoop.court.error.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Error> runtimeExceptionHandle(RuntimeException ex){
        Error error = Error.builder().message(ex.getMessage()).code("500-Internal Server Error").build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Error> requestExceptionHandle(RequestException ex){
        Error error = Error.builder().message(ex.getMessage()).code("400-Bad Request").build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
