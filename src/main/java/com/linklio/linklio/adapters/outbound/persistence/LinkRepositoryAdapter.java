package com.linklio.linklio.adapters.outbound.persistence;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaIconEntity;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaLinkEntity;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.exceptions.IconNotFoundException;
import com.linklio.linklio.application.exceptions.LinkNotFoundException;
import com.linklio.linklio.application.exceptions.UserNotFoundException;
import com.linklio.linklio.application.ports.out.linkPorts.DeleteLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.UpdateLinkPort;
import com.linklio.linklio.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LinkRepositoryAdapter implements SaveLinkPort, LoadLinkPort, DeleteLinkPort, UpdateLinkPort {
    private final JpaLinkRepository jpaLinkRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaIconRepository jpaIconRepository;
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
    public Optional<LinkResponse> updateById(Long id, LinkRequest linkRequest){
        JpaLinkEntity linkEntity = jpaLinkRepository.findById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));
        JpaUserEntity userEntity = jpaUserRepository.findById(linkRequest.getUserId())
                        .orElseThrow(() -> new UserNotFoundException(linkRequest.getUserId()));
        linkEntity.setUser(userEntity);
        linkEntity.setLinkText(linkRequest.getLinkText());
        linkEntity.setUrl(linkRequest.getUrl());
        linkEntity.setBgColor(linkRequest.getBgColor());
        linkEntity.setTextColor(linkRequest.getTextColor());
        linkEntity.setBorderColor(linkRequest.getBorderColor());
        linkEntity.setHoverBgColor(linkRequest.getHoverBgColor());
        linkEntity.setHoverBorderColor(linkRequest.getHoverBorderColor());
        linkEntity.setFontStyle(linkRequest.getFontStyle());
        linkEntity.setVisible(linkRequest.isVisible());
        linkEntity.setUpdatedAt(LocalDateTime.now());

        if (linkRequest.getIconId() != null) {
            JpaIconEntity iconEntity = jpaIconRepository.findById(linkRequest.getIconId())
                    .orElseThrow(() -> new IconNotFoundException(linkRequest.getIconId()));
            linkEntity.setIcon(iconEntity);
        }

        JpaLinkEntity updated = jpaLinkRepository.save(linkEntity);

        return Optional.of(linkMapper.toResponse(updated));


    }
}
