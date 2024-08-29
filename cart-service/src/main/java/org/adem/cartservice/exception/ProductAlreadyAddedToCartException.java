package org.adem.cartservice.exception;

public class ProductAlreadyAddedToCartException extends RuntimeException{
    public ProductAlreadyAddedToCartException(String message) {
        super(message);
    }
}
