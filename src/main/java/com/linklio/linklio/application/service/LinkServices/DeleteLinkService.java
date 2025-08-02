package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.application.exceptions.LinkNotFoundException;
import com.linklio.linklio.application.exceptions.UnauthorizedAccessException;
import com.linklio.linklio.application.ports.out.linkPorts.DeleteLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.domain.model.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteLinkService {
    private final LoadLinkPort loadLinkPort;
    private final DeleteLinkPort deleteLinkPort;

    public void deleteLink(Long id, String email){
        Link link = loadLinkPort.loadById(id)
                .orElseThrow(() -> new LinkNotFoundException(id));

        if(!link.getUser().getEmail().equals(email)){
            throw new UnauthorizedAccessException();
        }

        deleteLinkPort.deleteById(id);

    }
}
