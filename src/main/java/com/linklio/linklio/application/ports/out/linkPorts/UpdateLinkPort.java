package com.linklio.linklio.application.ports.out.linkPorts;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;

import java.util.Optional;

public interface UpdateLinkPort {
    Optional<LinkResponse> updateById(Long id, LinkRequest request);
}
