package org.adem.reportservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ReportNotFoundException.class)
    public Map<String,String> handleReportNotFound(ReportNotFoundException e){
        Map<String ,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleCustomerNotFound(CustomerNotFoundException e){
        Map<String ,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(ReportLimitExceedException.class)
    public Map<String,String> handleReportLimitExceed(ReportLimitExceedException e){
        Map<String ,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFound(ProductNotFoundException e){
        Map<String ,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(ReportTypeNotFoundException.class)
    public Map<String,String> handleReportTypeNotFound(ReportTypeNotFoundException e){
        Map<String ,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }
}
