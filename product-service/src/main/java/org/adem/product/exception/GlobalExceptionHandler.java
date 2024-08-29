package org.adem.product.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFound(ProductNotFoundException e){
        Map<String,String > exc = new HashMap<>();
        exc.put("message",e.getMessage());
        return exc;
    }
}
