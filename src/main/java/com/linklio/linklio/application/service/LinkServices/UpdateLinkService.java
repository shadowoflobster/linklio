package com.linklio.linklio.application.service.LinkServices;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.application.ports.out.linkPorts.UpdateLinkPort;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateLinkService {
    private final UpdateLinkPort updateLinkPort;

    public Optional<LinkResponse> updateLink(Long id, LinkRequest linkRequest){
        return updateLinkPort.updateById(id, linkRequest);
    }
}
