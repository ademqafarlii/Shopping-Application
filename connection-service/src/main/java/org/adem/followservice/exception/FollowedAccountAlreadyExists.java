package org.adem.followservice.exception;

public class FollowedAccountAlreadyExists extends RuntimeException{
    public FollowedAccountAlreadyExists(String message) {
        super(message);
    }
}
