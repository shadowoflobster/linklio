package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaUserEntity;
import com.linklio.linklio.domain.model.Role;
import com.linklio.linklio.domain.model.Subscription;
import com.linklio.linklio.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toDomain(JpaUserEntity entity){
        if(entity==null) return null;

        Set<Role>  shallowRoleSet = entity.getRoles().stream()
                .map(subRole->new Role(subRole.getId(), null))
                .collect(Collectors.toSet());

        Set<Subscription> shallowSubSet = entity.getSubscriptions().stream()
                .map(subSub -> new Subscription(subSub.getId(),null,null,null,null,false))
                .collect(Collectors.toSet());

        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.isVerified(),
                shallowRoleSet,
                shallowSubSet
        );
    }

    public JpaUserEntity toEntity(User user){
        if(user==null)return null;

        JpaUserEntity entity = new JpaUserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setVerified(user.isVerified());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setUsername(user.getUserName());

        return entity;
    }
}
