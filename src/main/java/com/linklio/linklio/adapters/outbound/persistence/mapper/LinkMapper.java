package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
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

       Icon shallowIcon = new Icon(); //Build icon later!!!

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
        User user = new User();
        user.setId(request.getUserId());
        return new Link(
                null,
                request.getLinkText(),
                request.getUrl(),
                user,
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
            JpaIconEntity iconEntity = new JpaIconEntity();
            iconEntity.setId(link.getIcon().getId());
            iconEntity.setIconUrl(link.getIcon().getIconUrl());
            iconEntity.setDescription(link.getIcon().getDescription());
            entity.setIcon(iconEntity);
        }
        return entity;

    }
}
