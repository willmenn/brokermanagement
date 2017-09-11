package com.brokermanagement.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException(String msg) {
        super(msg);
    }
}
