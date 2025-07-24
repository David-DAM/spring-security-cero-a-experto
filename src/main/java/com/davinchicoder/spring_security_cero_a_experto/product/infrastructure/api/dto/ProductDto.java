package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String provider;
    private List<ReviewDto> reviews;
    private List<String> categories;

}
