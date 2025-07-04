package com.linklio.linklio.application.service;

import com.linklio.linklio.adapters.outbound.persistence.JpaUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final JpaUserRepository userRepository;

}
