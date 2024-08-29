package org.adem.followservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String, String> handleCustomerNotFound(CustomerNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(FollowNotFoundException.class)
    public Map<String, String> handleFollowNotFound(FollowNotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(FollowedAccountAlreadyExists.class)
    public Map<String, String> handleFollowedAccountAlreadyExists(FollowedAccountAlreadyExists e) {
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
    @ExceptionHandler(FollowerNotFoundException.class)
    public Map<String,String> handleFollowerNotFound(FollowerNotFoundException e){
        Map<String,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(YouAreAlreadyFollowingException.class)
    public Map<String,String> handleYouAreAlreadyFollowing(YouAreAlreadyFollowingException e){
        Map<String,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }
}
