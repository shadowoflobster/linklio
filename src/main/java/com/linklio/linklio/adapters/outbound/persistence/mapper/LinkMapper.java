package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaIconEntity;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaLinkEntity;
import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import com.linklio.linklio.domain.model.Icon;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper {
    public Link toDomain(JpaLinkEntity entity){
       if (entity == null) return null;
       Icon shallowIcon = null;
        if (entity.getIcon() != null) {
            shallowIcon = new Icon(
                    entity.getIcon().getId(),
                    entity.getIcon().getIconUrl(),
                    entity.getIcon().getDescription()
            );
        }


       User shallowUser = new User();
       shallowUser.setId(entity.getUser().getId());

       return new Link(
               entity.getId(),
               entity.getLinkText(),
               entity.getUrl(),
               shallowUser,
               entity.getBgColor(),
               entity.getTextColor(),
               entity.getBorderColor(),
               entity.getHoverBgColor(),
               entity.getHoverBorderColor(),
               entity.getFontStyle(),
               shallowIcon,
               entity.isVisible(),
               entity.getCreatedAt(),
               entity.getUpdatedAt()
       );
}

    public Link toDomain(LinkRequest request, Icon icon){
        return new Link(
                null,
                request.getLinkText(),
                request.getUrl(),
                null,
                request.getBgColor(),
                request.getTextColor(),
                request.getBorderColor(),
                request.getHoverBgColor(),
                request.getHoverBorderColor(),
                request.getFontStyle(),
                icon,
                request.isVisible(),
                null,
                null
        );
    }


    public JpaLinkEntity toEntity(Link link) {
        if (link == null) return null;

        JpaLinkEntity entity = new JpaLinkEntity();
        entity.setId(link.getId());
        entity.setLinkText(link.getLinkText());
        entity.setUrl(link.getUrl());
        entity.setBgColor(link.getBgColor());
        entity.setTextColor(link.getTextColor());
        entity.setBorderColor(link.getBorderColor());
        entity.setHoverBgColor(link.getHoverBgColor());
        entity.setHoverBorderColor(link.getHoverBorderColor());
        entity.setFontStyle(link.getFontStyle());
        entity.setVisible(link.isVisible());
        entity.setCreatedAt(link.getCreatedAt());
        entity.setUpdatedAt(link.getUpdatedAt());

        if (link.getUser() != null) {
            JpaUserEntity userEntity = new JpaUserEntity();
            userEntity.setId(link.getUser().getId());
            entity.setUser(userEntity);
        }

        if (link.getIcon() != null) {
            entity.setIcon(new JpaIconEntity(link.getIcon().getId())); // reference only by ID
        }
        return entity;

    }

    public LinkResponse toResponse(Link link) {
        LinkResponse response = new LinkResponse();
        response.setId(link.getId());
        response.setLinkText(link.getLinkText());
        response.setUrl(link.getUrl());
        response.setBgColor(link.getBgColor());
        response.setTextColor(link.getTextColor());
        response.setBorderColor(link.getBorderColor());
        response.setHoverBgColor(link.getHoverBgColor());
        response.setHoverBorderColor(link.getHoverBorderColor());
        response.setFontStyle(link.getFontStyle());
        response.setVisible(link.isVisible());
        response.setCreatedAt(link.getCreatedAt());
        response.setUpdatedAt(link.getUpdatedAt());
        response.setIcon(link.getIcon());
        response.setOwnerEmail(link.getUser().getEmail());

        return response;
    }

    public LinkResponse toResponse(JpaLinkEntity entity) {
        Link domainLink = toDomain(entity);

        return toResponse(domainLink);
    }
}
