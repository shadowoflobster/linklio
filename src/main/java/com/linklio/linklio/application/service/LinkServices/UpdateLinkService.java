package com.linklio.linklio.application.service.LinkServices;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.exceptions.IconNotFoundException;
import com.linklio.linklio.application.exceptions.LinkNotFoundException;
import com.linklio.linklio.application.exceptions.UnauthorizedAccessException;
import com.linklio.linklio.application.exceptions.UserNotFoundException;
import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.UpdateLinkPort;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.Icon;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateLinkService {
    private final UpdateLinkPort updateLinkPort;
    private final LoadUserPort loadUserPort;
    private final LoadLinkPort loadLinkPort;
    private final LoadIconPort loadIconPort;
    private final SaveLinkPort saveLinkPort;
    private final LinkMapper linkMapper;

    public LinkResponse updateLink(Long id, LinkRequest linkRequest, String email){
        User user = loadUserPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        Link link = loadLinkPort.loadById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));

        if (!link.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException();
        }

        link.updateFromRequest(linkRequest);

        if (linkRequest.getIconId() != null) {
            Icon icon = loadIconPort.findById(linkRequest.getIconId())
                    .orElseThrow(() -> new IconNotFoundException(linkRequest.getIconId()));
            link.setIcon(icon);
        } else {
            link.setIcon(null);
        }

        Link updated = saveLinkPort.save(link);
        return linkMapper.toResponse(updated);

    }
}
