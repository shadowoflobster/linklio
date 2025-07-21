package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.outbound.persistence.JpaIconRepository;
import com.linklio.linklio.adapters.outbound.persistence.mapper.IconMapper;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.domain.model.Icon;
import com.linklio.linklio.domain.model.Link;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLinkService {
    private final SaveLinkPort saveLinkPort;
    private final JpaIconRepository iconRepository;
    private final IconMapper iconMapper;
    private final LinkMapper linkMapper;


    public Link createLink(LinkRequest request){
        Icon icon = null;
        if (request.getIconId() != null) {
            icon = iconRepository.findById(request.getIconId())
                    .map(iconMapper::toDomain)
                    .orElseThrow(() -> new RuntimeException("Icon not found with id: " + request.getIconId()));
        }

        Link link = linkMapper.toDomain(request,icon);

        return saveLinkPort.save(link);
    }

}
