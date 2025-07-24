package com.davinchicoder.spring_security_cero_a_experto.product.application.command.create;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductRequest implements Request<CreateProductResponse> {

    private String name;
    private String description;
    private Double price;
    private MultipartFile file;
}
