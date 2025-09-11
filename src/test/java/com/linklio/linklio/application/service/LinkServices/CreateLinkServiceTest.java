package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LinkRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.LinkResponse;
import com.linklio.linklio.adapters.outbound.persistence.mapper.LinkMapper;
import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.application.ports.out.linkPorts.SaveLinkPort;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.Icon;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class CreateLinkServiceTest {
    @InjectMocks
    private CreateLinkService createLinkService;
    @Mock
    private SaveLinkPort saveLinkPort = mock(SaveLinkPort.class);
    @Mock
    private LoadIconPort loadIconPort = mock(LoadIconPort.class);
    @Mock
    private LinkMapper linkMapper = mock(LinkMapper.class);
    @Mock
    private LoadUserPort loadUserPort = mock(LoadUserPort.class);


    @BeforeEach
    void setup() {
        createLinkService = new CreateLinkService(saveLinkPort, loadIconPort, linkMapper, loadUserPort);
    }


    @Test
    void shouldCreateLinkSuccessfullyWithIcon() {
        LinkRequest request = new LinkRequest();
        request.setIconId("instagram");
        request.setUrl("https://example.com");

        Icon icon = new Icon();
        User user = new User();
        Link link = new Link();
        Link savedLink = new Link();
        LinkResponse response = new LinkResponse();

        when(loadIconPort.findById("instagram")).thenReturn(Optional.of(icon));
        when(loadUserPort.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(linkMapper.toDomain(request,icon)).thenReturn(link);
        when(saveLinkPort.save(link)).thenReturn(savedLink);
        when(linkMapper.toResponse(savedLink)).thenReturn(response);

        LinkResponse result = createLinkService.createLink(request,"test@example.com");

        assertThat(result).isSameAs(response);

        verify(loadIconPort).findById("instagram");
        verify(loadUserPort).findByEmail("test@example.com");
        verify(linkMapper).toDomain(request, icon);
        verify(saveLinkPort).save(link);
        verify(linkMapper).toResponse(savedLink);

    }

}
