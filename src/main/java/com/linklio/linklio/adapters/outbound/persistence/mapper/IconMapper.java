package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaIconEntity;
import com.linklio.linklio.domain.model.Icon;
import org.springframework.stereotype.Component;

@Component
public class IconMapper {
    public Icon toDomain(JpaIconEntity entity){
        if (entity == null) return null;

        Icon icon = new Icon();
        icon.setId(entity.getId());
        icon.setIconUrl(entity.getIconUrl());
        icon.setDescription(entity.getDescription());

        return icon;
    }
}
