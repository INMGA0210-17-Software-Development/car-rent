package org.example.carrental.service;

import org.example.carrental.dto.LoginRequest;
import org.example.carrental.dto.LoginResponse;
import org.example.carrental.dto.RegisterRequest;
import org.example.carrental.model.User;
import org.example.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole(),
                "Login success"
        );
    }
}
