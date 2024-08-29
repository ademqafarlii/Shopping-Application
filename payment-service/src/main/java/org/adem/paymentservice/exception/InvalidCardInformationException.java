package org.adem.paymentservice.exception;

public class InvalidCardInformationException extends RuntimeException{
    public InvalidCardInformationException(String message) {
        super(message);
    }
}
