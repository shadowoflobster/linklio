package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.mapper.IconMapper;
import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.domain.model.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IconRepositoryAdapter implements LoadIconPort {
    private final JpaIconRepository iconRepository;
    private final IconMapper iconMapper;

    public Optional<Icon> findById(String id) {
        return iconRepository.findById(id)
                .map(iconMapper::toDomain);
    }
}
