package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.authentication;

import com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.service.JwtService;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.AuthenticationPort;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String authenticate(String username, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        UserEntity entity = (UserEntity) authenticate.getPrincipal();

        return jwtService.generateToken(entity);
    }
}
