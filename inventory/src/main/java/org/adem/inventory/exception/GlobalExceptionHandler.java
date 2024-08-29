package org.adem.inventory.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    public Map<String ,String> handleInventoryNotFound(InventoryNotFoundException e){
        Map<String,String> exc = new HashMap<>();
        exc.put("message",e.getMessage());
        return exc;
    }
}
