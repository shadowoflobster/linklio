package com.linklio.linklio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subscription {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User user;
    private Plan plan;
    private boolean isActive;
}
