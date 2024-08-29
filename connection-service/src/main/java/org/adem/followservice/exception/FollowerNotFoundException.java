package org.adem.followservice.exception;

public class FollowerNotFoundException extends RuntimeException{
    public FollowerNotFoundException(String message) {
        super(message);
    }
}
