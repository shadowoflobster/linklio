package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.JpaIconRepository;
import com.linklio.linklio.adapters.outbound.persistence.JpaUserRepository;
import com.linklio.linklio.adapters.outbound.persistence.mapper.IconMapper;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.adapters.outbound.persistence.mapper.UserMapper;
import com.linklio.linklio.application.exceptions.IconNotFoundException;
import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.Icon;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateLinkService {
    private final SaveLinkPort saveLinkPort;
    private final LoadIconPort loadIconPort;
    private final LinkMapper linkMapper;
    private final LoadUserPort loadUserPort;



    public LinkResponse createLink(LinkRequest request, String email){
        Icon icon = null;
        if (request.getIconId() != null) {
            icon = loadIconPort.findById(request.getIconId())
                    .orElseThrow(() -> new IconNotFoundException(request.getIconId()));
        }

        Link link = linkMapper.toDomain(request,icon);

        User user = loadUserPort.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        link.setUser(user);

        Link savedLink = saveLinkPort.save(link);

        return linkMapper.toResponse(savedLink);
    }

}
