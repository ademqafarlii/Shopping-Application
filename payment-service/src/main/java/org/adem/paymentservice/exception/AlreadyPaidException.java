package org.adem.paymentservice.exception;

public class AlreadyPaidException extends RuntimeException{
    public AlreadyPaidException(String message) {
        super(message);
    }
}
