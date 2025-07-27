package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
