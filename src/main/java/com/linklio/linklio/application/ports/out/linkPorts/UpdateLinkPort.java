package com.linklio.linklio.application.ports.out.linkPorts;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.domain.model.Link;

import java.util.Optional;

public interface UpdateLinkPort {
    Optional<LinkResponse> updateById(Link link);
}
