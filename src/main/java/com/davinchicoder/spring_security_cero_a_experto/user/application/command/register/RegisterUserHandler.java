package com.davinchicoder.spring_security_cero_a_experto.user.application.command.register;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {


    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {
        return null;
    }

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }
}
