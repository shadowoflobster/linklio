package com.linklio.linklio.application.exceptions;

public class LinkNotFoundException extends RuntimeException {
    public LinkNotFoundException(Long id) {
        super("Link not found with id: " + id);
    }
}