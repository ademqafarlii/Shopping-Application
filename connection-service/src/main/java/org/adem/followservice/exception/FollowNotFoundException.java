package org.adem.followservice.exception;

public class FollowNotFoundException extends RuntimeException{
    public FollowNotFoundException(String message) {
        super(message);
    }
}
