package com.linklio.linklio.adapters.outbound.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles", schema = "exam_zenith")
@Getter
@Setter
public class JpaRoleEntity {
    @Id
    private Long id;

    @Column
    private String name;
}
