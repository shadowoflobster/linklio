package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.mapper.UserMapper;
import com.linklio.linklio.application.ports.out.LoadUserPort;
import com.linklio.linklio.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements LoadUserPort {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(jpaEntity ->userMapper.toDomain(jpaEntity));
    }



}
