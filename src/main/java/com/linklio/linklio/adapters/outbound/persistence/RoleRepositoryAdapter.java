package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.mapper.RoleMapper;
import com.linklio.linklio.application.ports.out.rolePorts.LoadRolePort;
import com.linklio.linklio.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements LoadRolePort {
    private final JpaRoleRepository jpaRoleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByRoleName(name)
                .map(roleMapper::toDomain);
    }
}
