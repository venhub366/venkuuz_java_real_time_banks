package com.javaexpress.cards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CardsCustomExceptionhandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        var errorsList = ex.getFieldErrors();
        errorsList.stream()
                .forEach(
                        error -> errorsMap.put(error.getField(), error.getDefaultMessage())
                );
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> exceptionMap = new HashMap<>();
        exceptionMap.put(ex.getMessage(), ex.getLocalizedMessage());
        return new ResponseEntity<>(exceptionMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
