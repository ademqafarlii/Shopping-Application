package org.adem.customerservice.exception;

public class UsernameHasAlreadyBeenTaken extends RuntimeException {
    public UsernameHasAlreadyBeenTaken(String message) {
        super(message);
    }
}
