package com.davinchicoder.spring_security_cero_a_experto.product.domain.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("The product with id " + id + " was not found");
    }
}
