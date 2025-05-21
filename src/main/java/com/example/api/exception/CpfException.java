package com.example.api.exception;

public class CpfException extends RuntimeException{
    public CpfException() {
    }

    public CpfException(String message) {
        super(message);
    }
}
