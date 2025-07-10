package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.LoginRequest;
import com.linklio.linklio.adapters.inbound.rest.dto.RegisterRequest;
import com.linklio.linklio.application.service.UserServices.CreateUserService;
import com.linklio.linklio.application.service.UserServices.LoginResponse;
import com.linklio.linklio.application.service.UserServices.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
private final CreateUserService createUserService;
private final LoginService loginService;

@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
    createUserService.register(request);
    return ResponseEntity.ok("User Registered successfully");
}

@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
    LoginResponse response = loginService.login(request);
    return ResponseEntity.ok(response);
}

}
