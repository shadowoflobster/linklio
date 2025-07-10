package com.linklio.linklio.adapters.outbound.persistence.mapper;

import com.linklio.linklio.adapters.outbound.persistence.entity.JpaRoleEntity;
import com.linklio.linklio.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toDomain(JpaRoleEntity entity){
        if(entity==null) return null;
        return new Role(entity.getId(), entity.getRoleName());
    }

    public JpaRoleEntity toEntity(Role role){
        if(role == null) return null;

        JpaRoleEntity entity= new JpaRoleEntity();
        entity.setId(role.getId());
        entity.setRoleName(role.getName());
        return entity;
    }
}
