package com.jh.ecommerce.product.exception;

public class ProductException extends RuntimeException {

    public ProductException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ProductException(String exMessage) {
        super(exMessage);
    }

}
