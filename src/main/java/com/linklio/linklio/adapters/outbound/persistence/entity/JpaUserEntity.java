package com.linklio.linklio.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "linklio")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "users_seq", allocationSize = 1)
@Getter
@Setter
public class JpaUserEntity {

    @Id
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private  String email;

    @Column(nullable = false)
    private String passwordHash;

    @Column
    private boolean verified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            schema = "linklio",
            joinColumns = @JoinColumn(name = ("user_id")),
            inverseJoinColumns = @JoinColumn(name = ("role_id"))
    )
    private Set<JpaRoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<JpaSubscriptionEntity> subscriptions = new HashSet<>();

}
