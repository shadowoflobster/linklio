package com.linklio.linklio.application.service.LinkServices;


import com.linklio.linklio.application.ports.out.linkPorts.DeleteLinkPort;
import com.linklio.linklio.application.ports.out.linkPorts.LoadLinkPort;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.Link;
import com.linklio.linklio.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteLinkServiceTest {
    @InjectMocks
    private DeleteLinkService deleteLinkService;
    @Mock
    private LoadLinkPort loadLinkPort;
    @Mock
    private DeleteLinkPort deleteLinkPort;
    @Mock
    private LoadUserPort loadUserPort;

    @Test
    void shouldDeleteLink(){
        Long linkId = 1L;
        Long userId = 2L;
        String userEmail = "test@example.com";
        Link link = new Link();

        link.setId(linkId);
        link.setUrl("example.com");

        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);
        link.setUser(user);

        when(loadLinkPort.loadById(linkId)).thenReturn(Optional.of(link));
        when(loadUserPort.findByEmail(userEmail)).thenReturn(Optional.of(user));

        deleteLinkService.deleteLink(linkId, userEmail);


        verify(loadLinkPort).loadById(linkId);
        verify(loadUserPort).findByEmail(userEmail);
        verify(deleteLinkPort).deleteById(linkId);
        verifyNoMoreInteractions(loadLinkPort, loadUserPort, deleteLinkPort);

    }
}
