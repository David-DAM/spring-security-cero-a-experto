package com.davinchicoder.spring_security_cero_a_experto.user.application.command.register;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import lombok.Data;

@Data
public class RegisterUserRequest implements Request<RegisterUserResponse> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
