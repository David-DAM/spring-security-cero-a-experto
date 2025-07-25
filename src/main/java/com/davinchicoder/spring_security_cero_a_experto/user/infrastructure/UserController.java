package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure;

import com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.service.JwtService;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.dto.LoginRequestDto;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User", description = "User API operations")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> getUserById(@RequestBody LoginRequestDto loginRequestDto) {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username(loginRequestDto.getEmail())
                .password(loginRequestDto.getPassword())
                .roles("USER")
                .build();

        String token = jwtService.generateToken(user);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(token);

        return ResponseEntity.ok(loginResponseDto);
    }
}
