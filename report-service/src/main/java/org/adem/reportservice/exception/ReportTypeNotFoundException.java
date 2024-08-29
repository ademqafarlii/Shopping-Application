package org.adem.reportservice.exception;

public class ReportTypeNotFoundException extends RuntimeException {
    public ReportTypeNotFoundException(String message) {
        super(message);
    }
}
