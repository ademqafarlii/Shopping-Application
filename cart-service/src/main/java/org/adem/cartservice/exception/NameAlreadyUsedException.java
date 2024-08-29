package org.adem.cartservice.exception;

public class NameAlreadyUsedException extends RuntimeException{
    public NameAlreadyUsedException(String message) {
        super(message);
    }
}
