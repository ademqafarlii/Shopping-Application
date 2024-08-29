package org.adem.customerservice.exception;

public class FraudulentCustomerException extends RuntimeException{
    public FraudulentCustomerException(String message) {
        super(message);
    }
}
