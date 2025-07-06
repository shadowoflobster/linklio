package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity,Long> {
    Optional<JpaUserEntity> findByEmail(String email);
}
