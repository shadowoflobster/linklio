package com.linklio.linklio.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plans", schema = "linklio")
@Getter
@Setter
public class JpaPlanEntity {
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "link_limit")
    private Integer linkLimit;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private Set<JpaSubscriptionEntity> subscriptions = new HashSet<>();

}
