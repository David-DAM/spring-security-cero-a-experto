package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Long id;
    private String comment;
    private Integer score;

}
