package org.adem.supportservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ComplaintNotFoundException.class)
    public Map<String,String> handleComplaintNotFound(ComplaintNotFoundException e){
        Map<String,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }
}
