package com.linklio.linklio.application.ports.out;
import com.linklio.linklio.domain.model.User;

import java.util.Optional;

public interface LoadUserPort {
    Optional<User> findById(Long id);
}
