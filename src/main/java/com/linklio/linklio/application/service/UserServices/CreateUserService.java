package com.linklio.linklio.application.service.UserServices;

import com.linklio.linklio.adapters.inbound.rest.dto.RegisterRequest;
import com.linklio.linklio.application.ports.out.rolePorts.LoadRolePort;
import com.linklio.linklio.application.ports.out.userPorts.SaveUserPort;
import com.linklio.linklio.domain.model.Role;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final SaveUserPort saveUserPort;
    private final LoadRolePort loadRolePort;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request){
        Role defaultRole = loadRolePort.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        user.setRoles(new HashSet<>());
        user.getRoles().add(defaultRole);

        return saveUserPort.save(user);
    }

}
