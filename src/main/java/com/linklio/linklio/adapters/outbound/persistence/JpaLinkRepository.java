package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaLinkRepository extends JpaRepository<JpaLinkEntity,Long> {
    List<JpaLinkEntity> findByUserId(Long userId);
}
