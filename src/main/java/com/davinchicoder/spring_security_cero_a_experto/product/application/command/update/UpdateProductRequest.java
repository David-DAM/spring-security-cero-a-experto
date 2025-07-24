package com.davinchicoder.spring_security_cero_a_experto.product.application.command.update;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import com.davinchicoder.spring_security_cero_a_experto.review.domain.Review;
import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String provider;
    private Review review;
    private Long categoryId;
}
