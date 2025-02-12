package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedUserException extends RuntimeException {
    private String message;

    public UnauthorizedUserException() {}

    public UnauthorizedUserException(String msg) {
        super(msg);
        this.message = msg;
    }
}
