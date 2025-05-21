package com.example.api.exception;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound() {
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
