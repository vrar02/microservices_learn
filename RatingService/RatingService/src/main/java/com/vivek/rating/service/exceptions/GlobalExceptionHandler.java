package com.vivek.rating.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException exception){

        Map hashMap=new HashMap();
        hashMap.put("message",exception.getMessage());
        hashMap.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
    }

}
