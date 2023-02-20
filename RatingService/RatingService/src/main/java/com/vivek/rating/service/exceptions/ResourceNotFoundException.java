package com.vivek.rating.service.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("rating resource not found");

    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
