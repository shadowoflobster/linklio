package com.linklio.linklio.adapters.inbound.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkRequest {

    @NotBlank
    private String linkText;

    @NotBlank
    private String url;

    @NotNull
    private Long userId;

    private String bgColor;
    private String textColor;
    private String borderColor;
    private String hoverBgColor;
    private String hoverBorderColor;
    private String fontStyle;

    private String iconId;

    private boolean visible;
}
