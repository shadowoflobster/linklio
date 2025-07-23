package com.linklio.linklio.adapters.outbound.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "links", schema = "linklio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "link_seq_gen", sequenceName = "links_seq", allocationSize = 1)
public class JpaLinkEntity {
    @Id
    @GeneratedValue(generator = "link_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "link_text")
    private String linkText;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private JpaUserEntity user;

    @Column(name="bg_color")
    private String bgColor;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "border_color")
    private String borderColor;

    @Column(name = "hover_bg_color")
    private String hoverBgColor;

    @Column(name = "hover_border_color")
    private String hoverBorderColor;

    @Column(name = "font_style")
    private String fontStyle;

    @ManyToOne
    @JoinColumn(name = "icon_id")
    private JpaIconEntity icon;

    @Column(name = "is_visible")
    private boolean isVisible;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
