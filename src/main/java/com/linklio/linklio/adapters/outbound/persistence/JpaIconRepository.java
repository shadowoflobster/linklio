package com.linklio.linklio.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIconRepository extends JpaRepository<JpaIconRepository,String> {
}
