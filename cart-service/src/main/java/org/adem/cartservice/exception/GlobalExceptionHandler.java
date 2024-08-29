package org.adem.cartservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameAlreadyUsedException.class)
    public Map<String,String> handleNameAlreadyUsed(NameAlreadyUsedException e){
        Map<String,String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(ProductAlreadyAddedToCartException.class)
    public Map<String,String> handleProductAlreadyAdded(ProductAlreadyAddedToCartException e){
        Map<String,String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFound(ProductNotFoundException e){
        Map<String,String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
