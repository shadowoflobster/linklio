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
public class Link {
    private Long id;
    private String linkText;
    private String url;
    private User user; // domain model, not the JPA entity
    private String bgColor;
    private String textColor;
    private String borderColor;
    private String hoverBgColor;
    private String hoverBorderColor;
    private String fontStyle;
    private Icon icon; // domain model
    private boolean isVisible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    }
