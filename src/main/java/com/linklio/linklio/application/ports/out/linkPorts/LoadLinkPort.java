package com.linklio.linklio.application.ports.out.linkPorts;

import com.linklio.linklio.domain.model.Link;

import java.util.Optional;

public interface LoadLinkPort {
    Optional<Link> loadById(Long id);
}
