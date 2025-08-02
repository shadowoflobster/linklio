package com.linklio.linklio.application.service.UserServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LoginRequest;
import com.linklio.linklio.adapters.inbound.security.JwtUtil;
import com.linklio.linklio.application.exceptions.InvalidEmailOrPassword;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
private final LoadUserPort loadUserPort;
private final PasswordEncoder passwordEncoder;

private final JwtUtil jwtUtil;

public LoginResponse login(LoginRequest loginRequest) throws Exception {
Optional<User> userOpt  =loadUserPort.findByEmail(loginRequest.getEmail());
User user = userOpt.orElseThrow(InvalidEmailOrPassword::new);
if(passwordEncoder.matches(loginRequest.getPassword(),user.getPasswordHash())){
    String token = jwtUtil.generateToken(user);
    return new LoginResponse(token);
}
throw new Exception("Invalid login");
}




}
