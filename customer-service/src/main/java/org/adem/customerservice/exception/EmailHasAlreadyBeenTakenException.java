package org.adem.customerservice.exception;

public class EmailHasAlreadyBeenTakenException extends RuntimeException{
    public EmailHasAlreadyBeenTakenException(String message) {
        super(message);
    }
}
