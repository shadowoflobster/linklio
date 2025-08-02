package com.linklio.linklio.adapters.inbound.rest.dto;

import com.linklio.linklio.domain.model.Icon;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkResponse {
    private Long id;
    private String linkText;
    private String url;

    // Styling
    private String bgColor;
    private String textColor;
    private String borderColor;
    private String hoverBgColor;
    private String hoverBorderColor;
    private String fontStyle;

    // Visibility and timestamps
    private boolean isVisible;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Associated icon
    private Icon icon;

    // Owner info (only safe fields!)
    private String ownerEmail;
}