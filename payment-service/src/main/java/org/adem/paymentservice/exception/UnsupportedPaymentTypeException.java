package org.adem.paymentservice.exception;

public class UnsupportedPaymentTypeException extends RuntimeException{
    public UnsupportedPaymentTypeException(String message) {
        super(message);
    }
}
