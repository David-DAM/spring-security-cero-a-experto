package com.davinchicoder.spring_security_cero_a_experto.productDetail.domain;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetail {

    private Long id;
    private String specifications;
    private String warranty;
    private String provider;

    private Product product;

}
