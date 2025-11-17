package org.example.carrental.controller;

import org.example.carrental.dto.LoginRequest;
import org.example.carrental.dto.LoginResponse;
import org.example.carrental.dto.RegisterRequest;
import org.example.carrental.model.User;
import org.example.carrental.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
