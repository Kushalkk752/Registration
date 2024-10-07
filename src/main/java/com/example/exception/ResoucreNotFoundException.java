package com.example.exception;

public class ResoucreNotFoundException extends RuntimeException{
    public ResoucreNotFoundException(String msg) {
        super(msg);
    }
}
