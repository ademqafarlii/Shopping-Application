package org.adem.ratingservice.exception;

public class AlreadyRatedException extends RuntimeException{
    public AlreadyRatedException(String message) {
        super(message);
    }
}
