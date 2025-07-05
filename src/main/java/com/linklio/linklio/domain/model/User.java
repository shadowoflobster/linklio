package com.linklio.linklio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String userName;
    private String email;
    private String passwordHash;
    private boolean isVerified;
    private Set<Role> roles = new HashSet<>();
    private Set<Subscription> subscriptions = new HashSet<>();

}
