package org.adem.reportservice.exception;

public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message) {
        super(message);
    }
}
