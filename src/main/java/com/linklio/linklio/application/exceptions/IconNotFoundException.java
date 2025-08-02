package com.linklio.linklio.application.exceptions;

public class IconNotFoundException extends RuntimeException{
    public IconNotFoundException(String id){
        super("Icon not found with id: "+id);
    }
}
