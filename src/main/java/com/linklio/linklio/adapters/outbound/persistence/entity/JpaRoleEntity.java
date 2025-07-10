package com.linklio.linklio.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles", schema = "linklio")
@Getter
@Setter
public class JpaRoleEntity {
    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;
}
