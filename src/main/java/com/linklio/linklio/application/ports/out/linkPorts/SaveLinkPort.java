package com.linklio.linklio.application.ports.out.linkPorts;

import com.linklio.linklio.domain.model.Link;

public interface SaveLinkPort {
    Link save(Link link);
}
