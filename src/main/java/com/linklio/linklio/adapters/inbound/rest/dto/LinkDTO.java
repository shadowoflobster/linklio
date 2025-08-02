package com.linklio.linklio.adapters.inbound.rest.dto;

import com.linklio.linklio.domain.model.Icon;

import java.time.LocalDateTime;

public class LinkDTO {
    private Long id;
    private String linkText;
    private String url;
    private String bgColor;
    private String textColor;
    private String borderColor;
    private String hoverBgColor;
    private String hoverBorderColor;
    private String fontStyle;
    private boolean isVisible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Icon icon;
    private String ownerEmail; // Or userId, whatever is safe/needed
}