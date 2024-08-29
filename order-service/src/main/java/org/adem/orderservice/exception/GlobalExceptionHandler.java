package org.adem.orderservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public Map<String,String> handleOrderNotFound(OrderNotFoundException e){
        Map<String,String> map= new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(ProductIsNotInStockException.class)
    public Map<String,String> handleProductIsNotInStock(ProductIsNotInStockException e){
        Map<String,String> map= new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }


}
