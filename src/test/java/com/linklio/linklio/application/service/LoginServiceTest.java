package com.linklio.linklio.application.service;

import com.linklio.linklio.adapters.inbound.rest.dto.LoginRequest;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.application.service.UserServices.LoginService;
import com.linklio.linklio.domain.model.Role;
import com.linklio.linklio.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;
    @Mock
    private LoadUserPort loadUserPort = mock(LoadUserPort.class);
    @Mock
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    @BeforeEach
    void setup() throws Exception{
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService(loadUserPort,passwordEncoder);

        Field secretField = LoginService.class.getDeclaredField("secretkey");
        secretField.setAccessible(true);
        secretField.set(loginService,"my-256-bit-secret-my-256-bit-secret");
    }

    @Test
    void login_successful() throws Exception{
        LoginRequest request = new LoginRequest("test@example.com", "password123");
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setId(1L);
        mockUser.setUserName("TestUser");
        mockUser.setPasswordHash("encodedPassword");
        mockUser.setRoles(Set.of(new Role(1L, "USER")));
        mockUser.setVerified(true);

        when(loadUserPort.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        var response = loginService.login(request);

        assertNotNull(response.getAccessToken());
        assertTrue(response.getAccessToken().startsWith("ey"));
    }


    @Test
    void login_fails_wrong_password() {
        LoginRequest request = new LoginRequest("test@example.com", "wrongpass");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPasswordHash("encodedPassword");

        when(loadUserPort.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches("wrongpass", "encodedPassword")).thenReturn(false);

        Exception exception = assertThrows(Exception.class, () -> loginService.login(request));
        assertEquals("Invalid login", exception.getMessage());
    }

    @Test
    void login_fails_email_not_found() {
        LoginRequest request = new LoginRequest("notfound@example.com", "any");

        when(loadUserPort.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> loginService.login(request));
        assertEquals("Invalid email or password", exception.getMessage());
    }


}
