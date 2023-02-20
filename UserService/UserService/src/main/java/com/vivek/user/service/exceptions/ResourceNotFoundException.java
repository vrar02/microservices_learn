package com.vivek.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    //extra properties can be written here
    public ResourceNotFoundException(){
        super("Resource not found on server");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

}
