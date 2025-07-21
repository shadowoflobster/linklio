package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaLinkEntity;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class LinkRepositoryAdapter implements SaveLinkPort {
    private final JpaLinkRepository jpaLinkRepository;
    private final LinkMapper linkMapper;


    @Override
    public Link save(Link link){
        JpaLinkEntity linkEntity = linkMapper.toEntity(link);
        JpaLinkEntity saved = jpaLinkRepository.save(linkEntity);
        return linkMapper.toDomain(saved);

    }
}
