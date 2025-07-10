package com.linklio.linklio.adapters.inbound.rest.controllers;

import com.linklio.linklio.adapters.inbound.rest.dto.RegisterRequest;
import com.linklio.linklio.application.service.UserServices.CreateUserService;
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

@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
    createUserService.register(request);
    return ResponseEntity.ok("User Registered successfully");
}

}
