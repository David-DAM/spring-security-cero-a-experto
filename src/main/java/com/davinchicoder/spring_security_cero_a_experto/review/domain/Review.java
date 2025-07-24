package com.davinchicoder.spring_security_cero_a_experto.review.domain;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {

    private Long id;
    private String comment;
    private Integer score;

    private Product product;

}
