package com.davinchicoder.spring_security_cero_a_experto.user.application.command.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserResponse {

    private String token;

}
