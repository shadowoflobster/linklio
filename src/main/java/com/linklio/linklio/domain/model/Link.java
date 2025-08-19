package com.linklio.linklio.domain.model;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
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

    public void updateFromRequest(LinkRequest linkRequest){
        this.setLinkText(linkRequest.getLinkText());
        this.setUrl(linkRequest.getUrl());
        this.setBgColor(linkRequest.getBgColor());
        this.setTextColor(linkRequest.getTextColor());
        this.setBorderColor(linkRequest.getBorderColor());
        this.setHoverBgColor(linkRequest.getHoverBgColor());
        this.setHoverBorderColor(linkRequest.getHoverBorderColor());
        this.setFontStyle(linkRequest.getFontStyle());
        this.setVisible(linkRequest.isVisible());
        this.setUpdatedAt(LocalDateTime.now());

    }
    }
