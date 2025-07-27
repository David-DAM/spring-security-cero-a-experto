package com.davinchicoder.spring_security_cero_a_experto.user.domain.port;

import com.davinchicoder.spring_security_cero_a_experto.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User upsert(User user);
}
