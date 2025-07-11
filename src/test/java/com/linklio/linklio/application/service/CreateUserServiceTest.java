package com.linklio.linklio.application.service;

import com.linklio.linklio.adapters.inbound.rest.dto.RegisterRequest;
import com.linklio.linklio.application.ports.out.rolePorts.LoadRolePort;
import com.linklio.linklio.application.ports.out.userPorts.SaveUserPort;
import com.linklio.linklio.application.service.UserServices.CreateUserService;
import com.linklio.linklio.domain.model.Role;
import com.linklio.linklio.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateUserServiceTest {
    SaveUserPort saveUserPort = mock(SaveUserPort.class);
    LoadRolePort loadRolePort = mock(LoadRolePort.class);
    PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    CreateUserService createUserService;

    @BeforeEach
    void setup(){
        createUserService = new CreateUserService(saveUserPort, loadRolePort, passwordEncoder);
    }

    @Test
    void shouldRegisterUserSuccessfully(){
        RegisterRequest request = new RegisterRequest();
        request.setUserName("testUser");
        request.setEmail("test@example.com");
        request.setPassword("testPassword");

        Role userRole=new Role();
        userRole.setName("USER");

        when(loadRolePort.findByName("USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode("testPassword")).thenReturn("encodedPassword");
        when(saveUserPort.save(any(User.class))).thenAnswer(i->i.getArguments()[0]);

        User savedUser = createUserService.register(request);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(saveUserPort).save(captor.capture());

        User capturedUser = captor.getValue();

        assertThat(capturedUser.getUserName()).isEqualTo("testUser");
        assertThat(capturedUser.getEmail()).isEqualTo("test@example.com");
        assertThat(capturedUser.getPasswordHash()).isEqualTo("encodedPassword");
        assertThat(capturedUser.isVerified()).isFalse();
        assertThat(capturedUser.getRoles()).contains(userRole);

        assertThat(savedUser).isSameAs(capturedUser);
    }


}

