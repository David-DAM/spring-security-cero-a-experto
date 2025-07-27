package com.davinchicoder.spring_security_cero_a_experto.user.application.command.login;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import lombok.Data;

@Data
public class LoginUserRequest implements Request<LoginUserResponse> {

    private String email;
    private String password;

}
