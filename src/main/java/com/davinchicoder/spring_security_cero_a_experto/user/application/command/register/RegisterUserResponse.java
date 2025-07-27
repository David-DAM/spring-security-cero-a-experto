package com.davinchicoder.spring_security_cero_a_experto.user.application.command.register;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserResponse {

    private String token;

}
