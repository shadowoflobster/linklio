package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.mapper.IconMapper;
import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.domain.model.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IconRepositoryAdapter implements LoadIconPort {
    private final JpaIconRepository iconRepository;
    private final IconMapper iconMapper;

    @Override
    public Optional<Icon> findById(String id) {
        return iconRepository.findById(id)
                .map(iconMapper::toDomain);
    }

    @Override
    public List<Icon> findAll(){
        return iconRepository.findAll()
                .stream()
                .map(iconMapper::toDomain)
                .toList();
    }
}
