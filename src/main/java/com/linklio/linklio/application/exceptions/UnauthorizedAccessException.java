package com.linklio.linklio.application.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException() {
        super("You are not authorized to access this resource.");
    }
}