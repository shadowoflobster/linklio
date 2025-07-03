package com.linklio.linklio.adapters.outbound.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "linklio")
@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
@Getter
@Setter
public class JpaUserEntity {

    @Id
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String userName;

    @Column
    private  String email;

    @Column
    private String password_hash;

    @Column
    private boolean is_verified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            schema = "linklio",
            joinColumns = @JoinColumn(name = ("user_id")),
            inverseJoinColumns = @JoinColumn(name = ("role_id"))
    )
    private Set<JpaRoleEntity> roles = new HashSet<>();

}
