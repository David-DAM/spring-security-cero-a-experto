package com.davinchicoder.spring_security_cero_a_experto.product.application.query.getById;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetProductByIdResponse {

    private Product product;
}

