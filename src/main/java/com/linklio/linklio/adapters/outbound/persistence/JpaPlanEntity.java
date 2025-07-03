package com.linklio.linklio.adapters.outbound.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

}
