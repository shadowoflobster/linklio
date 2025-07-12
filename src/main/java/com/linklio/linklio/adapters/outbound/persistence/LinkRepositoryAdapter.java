package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LinkRepositoryAdapter implements SaveLinkPort {
    private JpaLinkRepository jpaLinkRepository;


    @Override
    public Link save(Link link){
        return new Link();
    }
}
