package com.linklio.linklio.application.exceptions;

public class InvalidEmailOrPassword extends RuntimeException{
    public InvalidEmailOrPassword(){
        super("Invalid Email Or Password");
    }
}
