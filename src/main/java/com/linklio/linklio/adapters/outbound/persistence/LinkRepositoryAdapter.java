package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaLinkEntity;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.ports.out.linkPorts.DeleteLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.UpdateLinkPort;
import com.linklio.linklio.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LinkRepositoryAdapter implements SaveLinkPort, LoadLinkPort, DeleteLinkPort, UpdateLinkPort {
    private final JpaLinkRepository jpaLinkRepository;
    private final LinkMapper linkMapper;


    @Override
    public Link save(Link link){
        JpaLinkEntity linkEntity = linkMapper.toEntity(link);
        JpaLinkEntity saved = jpaLinkRepository.save(linkEntity);
        return linkMapper.toDomain(saved);

    }

    @Override
    public Optional<Link> loadById(Long id){
        return jpaLinkRepository.findById(id)
                .map(linkMapper::toDomain);
    }


    @Override
    public void deleteById(Long id){
        jpaLinkRepository.deleteById(id);
    }

    @Override
    public Optional<LinkResponse> updateById(Link link){
        JpaLinkEntity linkEntity = linkMapper.toEntity(link);
        JpaLinkEntity updated = jpaLinkRepository.save(linkEntity);

        return Optional.of(linkMapper.toResponse(updated));


    }
}
