package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientErrorException extends RuntimeException{
    private String message;

    public ClientErrorException() {}

    public ClientErrorException(String msg) {
        super(msg);
        this.message = msg;
    }
}
