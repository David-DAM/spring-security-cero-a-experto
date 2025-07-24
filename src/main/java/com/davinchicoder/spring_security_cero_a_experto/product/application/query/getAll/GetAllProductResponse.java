package com.davinchicoder.spring_security_cero_a_experto.product.application.query.getAll;

import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationResult;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetAllProductResponse {

    private PaginationResult<Product> productsPage;
}

