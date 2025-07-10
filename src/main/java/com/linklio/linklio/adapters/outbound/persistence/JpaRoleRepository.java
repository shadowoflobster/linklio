package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<JpaRoleEntity,Long> {
    Optional<JpaRoleEntity> findByRoleName(String roleName);
}
