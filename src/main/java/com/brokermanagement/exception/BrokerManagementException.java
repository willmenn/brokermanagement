package com.brokermanagement.exception;


import lombok.Getter;

@Getter
public class BrokerManagementException extends RuntimeException {
    private String id;

    public BrokerManagementException(String message, String id) {
        super(message);
        this.id = id;
    }
}
