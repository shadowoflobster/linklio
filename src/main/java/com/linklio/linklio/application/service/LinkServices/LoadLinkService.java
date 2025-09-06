package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.exceptions.LinkNotFoundException;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadLinkService {
    private final LoadLinkPort loadLinkPort;

    private final LinkMapper linkMapper;

    public LinkResponse loadLinkById(Long id){
        Link link = loadLinkPort.loadById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));
        return linkMapper.toResponse(link);
    }

    public List<LinkResponse> loadLinkByUserId(Long userId){
        List<Link> links = loadLinkPort.loadByUserId(userId);
        return links.stream()
                .map(linkMapper::toResponse)
                .toList();
    }
}
