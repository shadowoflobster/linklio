package com.linklio.linklio.application.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email){
        super("User not found with email: "+email);
    }
}
