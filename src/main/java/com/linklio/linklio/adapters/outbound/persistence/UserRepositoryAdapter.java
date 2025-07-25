package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import com.linklio.linklio.adapters.outbound.persistence.mapper.UserMapper;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.application.ports.out.userPorts.SaveUserPort;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements LoadUserPort, SaveUserPort {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }


    @Override
    public User save(User user) {
        JpaUserEntity entity = userMapper.toEntity(user);
        JpaUserEntity saved = jpaUserRepository.save(entity);
        return userMapper.toDomain(saved);
    }
}
