package com.linklio.linklio.application.ports.out.rolePorts;


import com.linklio.linklio.domain.model.Role;

import java.util.Optional;

public interface LoadRolePort {
    Optional<Role> findByName(String name);
}
