package com.brokermanagement.exception;


import lombok.Getter;

@Getter
public class BrokerManagementException extends RuntimeException {
    private String brokerId;

    public BrokerManagementException(String message, String brokerId) {
        super(message);
        this.brokerId = brokerId;
    }
}
