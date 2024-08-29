package org.adem.customerservice.exception;

public class PasswordCannotBeSameWithPreviousOneException extends RuntimeException {
    public PasswordCannotBeSameWithPreviousOneException(String message) {
        super(message);
    }
}
