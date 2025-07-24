package com.davinchicoder.spring_security_cero_a_experto.product.application.command.create;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateProductResponse {

    private Product product;
}
