package com.davinchicoder.spring_security_cero_a_experto.product.domain.entity;

import com.davinchicoder.spring_security_cero_a_experto.category.domain.Category;
import com.davinchicoder.spring_security_cero_a_experto.productDetail.domain.ProductDetail;
import com.davinchicoder.spring_security_cero_a_experto.review.domain.Review;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    private ProductDetail productDetail;
    private List<Review> reviews;
    private List<Category> categories;

}
