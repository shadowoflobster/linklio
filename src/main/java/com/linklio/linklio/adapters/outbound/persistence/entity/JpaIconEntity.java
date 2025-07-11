package com.linklio.linklio.adapters.outbound.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "icons", schema = "linklio")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JpaIconEntity {
    @Id
    private String id;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column
    private String description;
}
