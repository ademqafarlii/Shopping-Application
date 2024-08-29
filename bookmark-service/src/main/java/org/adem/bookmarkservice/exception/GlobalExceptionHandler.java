package org.adem.bookmarkservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookmarkNotFoundException.class)
    public Map<String, String> handleBookmarkNotFound(BookmarkNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String, String> handleProductNotFound(ProductNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(AlreadyAddedBookmarkException.class)
    public Map<String, String> handleAlreadyAddedBookmark(AlreadyAddedBookmarkException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }


}
