package org.adem.followservice.exception;

public class YouAreAlreadyFollowingException extends RuntimeException{
    public YouAreAlreadyFollowingException(String message) {
        super(message);
    }
}
