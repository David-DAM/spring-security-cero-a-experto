package com.davinchicoder.spring_security_cero_a_experto.user.domain.port;

public interface PasswordEncoderPort {

    String encode(String password);
}
