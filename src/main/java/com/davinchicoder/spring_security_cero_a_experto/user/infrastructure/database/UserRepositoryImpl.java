package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database;

import com.davinchicoder.spring_security_cero_a_experto.user.domain.User;
import com.davinchicoder.spring_security_cero_a_experto.user.domain.port.UserRepository;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.entity.UserEntity;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.mapper.UserEntityMapper;
import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.repository.QueryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository queryUserRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return queryUserRepository.findByEmail(email).map(userEntityMapper::mapToUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return queryUserRepository.findByEmail(email).isPresent();
    }

    @Override
    public User upsert(User user) {
        UserEntity userEntity = userEntityMapper.mapToUserEntity(user);
        UserEntity saved = queryUserRepository.save(userEntity);
        return userEntityMapper.mapToUser(saved);
    }
}
