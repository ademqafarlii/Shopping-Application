package org.adem.reportservice.exception;

public class ReportLimitExceedException extends RuntimeException{
    public ReportLimitExceedException(String message) {
        super(message);
    }
}
