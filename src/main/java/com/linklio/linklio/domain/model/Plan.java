package com.linklio.linklio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plan {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer linkLimit;
    private String description;
    private Set<Subscription> subscriptions = new HashSet<>();
}