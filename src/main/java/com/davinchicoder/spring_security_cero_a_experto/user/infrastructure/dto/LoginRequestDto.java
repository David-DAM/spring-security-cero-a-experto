package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;

}
