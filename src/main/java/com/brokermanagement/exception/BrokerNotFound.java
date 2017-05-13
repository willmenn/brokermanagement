package com.brokermanagement.exception;


public class BrokerNotFound extends BrokerManagementException {
    public BrokerNotFound(String message, String brokerId) {
        super(message, brokerId);
    }
}
