package org.adem.customerservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FraudulentCustomerException.class)
    public Map<String, String> handleFraudulentCustomer(FraudulentCustomerException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(EmailHasAlreadyBeenTakenException.class)
    public Map<String, String> handleEmailHasAlreadyBeenTaken(EmailHasAlreadyBeenTakenException e) {
        Map<String, String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(UsernameHasAlreadyBeenTaken.class)
    public Map<String, String> handleUsernameHasAlreadyBeenTaken(UsernameHasAlreadyBeenTaken e) {
        Map<String, String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleCustomerNotFound(CustomerNotFoundException e){
        Map<String,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

    @ExceptionHandler(PasswordCannotBeSameWithPreviousOneException.class)
    public Map<String,String> handlePasswordCannotBeSameWithPreviousOne(PasswordCannotBeSameWithPreviousOneException e){
        Map<String,String> map = new HashMap<>();
        map.put("message",e.getMessage());
        return map;
    }

}
