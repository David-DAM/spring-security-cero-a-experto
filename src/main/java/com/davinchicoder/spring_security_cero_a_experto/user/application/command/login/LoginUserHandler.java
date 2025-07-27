package com.davinchicoder.spring_security_cero_a_experto.user.application.command.login;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.AuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    private final AuthenticationPort authenticationPort;

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {

        String token = authenticationPort.authenticate(request.getEmail(), request.getPassword());

        return new LoginUserResponse(token);
    }

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }
}
