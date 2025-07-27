package com.davinchicoder.spring_security_cero_a_experto.user.application.command.register;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.User;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.UserRole;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.AuthenticationPort;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.PasswordEncoderPort;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationPort authenticationPort;

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {

        boolean existsByEmail = userRepository.existsByEmail(request.getEmail());

        if (existsByEmail) {
            throw new RuntimeException("El email ya existe");
        }

        String encoded = passwordEncoderPort.encode(request.getPassword());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoded)
                .role(UserRole.USER)
                .build();

        userRepository.upsert(user);

        String token = authenticationPort.authenticate(request.getEmail(), request.getPassword());

        return new RegisterUserResponse(token);
    }

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }
}
