package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDto {

    @NotBlank
    private String name;
    @Length(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    private String description;
    @DecimalMin(value = "0.01", inclusive = false)
    @DecimalMax(value = "999.99", inclusive = false)
    private Double price;
    private MultipartFile file;

}
