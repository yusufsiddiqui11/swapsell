package com.stackroute.userservice.exception;

public class ProductDoesNotExistsException extends Exception{
    public ProductDoesNotExistsException(String message) {
        super(message);
    }
}
