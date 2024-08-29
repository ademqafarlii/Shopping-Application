package org.adem.bookmarkservice.exception;

public class AlreadyAddedBookmarkException extends RuntimeException  {
    public AlreadyAddedBookmarkException(String message) {
        super(message);
    }
}
