package com.linklio.linklio.application.service.UserServices;

import com.linklio.linklio.adapters.inbound.rest.dto.LoginRequest;
import com.linklio.linklio.application.ports.out.userPorts.LoadUserPort;
import com.linklio.linklio.domain.model.Role;
import com.linklio.linklio.domain.model.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {
private final LoadUserPort loadUserPort;
private final PasswordEncoder passwordEncoder;

@Value("${jwt.secret-key}")
private String secretkey;

public LoginResponse login(LoginRequest loginRequest) throws Exception {
Optional<User> userOpt  =loadUserPort.findByEmail(loginRequest.getEmail());

User user = userOpt.orElseThrow(() -> new Exception("Invalid email or password"));

if(passwordEncoder.matches(loginRequest.getPassword(),user.getPasswordHash())){
return generateLoginResponse(user);
}
throw new Exception("Invalid login");
}

private LoginResponse generateLoginResponse(User user) throws Exception {
try {
    Date now = new Date();
    JWTClaimsSet claimsSet =new JWTClaimsSet.Builder()
            .subject(user.getEmail())
            .claim("user_id",user.getId())
            .claim("user_name",user.getUserName())
            .claim("role",user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
            .claim("is_verified",user.isVerified())
            .issuer("linklio")
            .expirationTime(new Date(now.getTime() + 3600_000))
            .build();
    JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
    SignedJWT signedJWT = new SignedJWT(header,claimsSet);
    signedJWT.sign(new MACSigner(secretkey.getBytes()));

    return new LoginResponse(signedJWT.serialize());
}catch (Exception e){
    throw new Exception("Failed to generate token", e);
}
}

}
