package com.vivek.user.service.exceptions;


import com.vivek.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){

        String message = exception.getMessage();
        ApiResponse apiresponse = ApiResponse.builder().message(message).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiresponse, HttpStatus.NOT_FOUND);
    }


}
