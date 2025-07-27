package com.davinchicoder.spring_security_cero_a_experto.user.application.command.login;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {


    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        return null;
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
