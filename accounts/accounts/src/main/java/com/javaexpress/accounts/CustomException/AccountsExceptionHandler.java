package com.javaexpress.accounts.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountsExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleCustomValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorsMap = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors().forEach(
                        e -> errorsMap.put(e.getField(), e.getDefaultMessage())
                );
        return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    }
}
