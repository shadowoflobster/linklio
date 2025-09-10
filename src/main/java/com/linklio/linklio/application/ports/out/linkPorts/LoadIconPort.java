package com.linklio.linklio.application.ports.out.linkPorts;

import com.linklio.linklio.domain.model.Icon;

import java.util.List;
import java.util.Optional;

public interface LoadIconPort {
    Optional<Icon> findById(String id);
    List<Icon> findAll();

}
