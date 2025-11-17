package org.example.carrental.dto;

import lombok.Data;
import org.example.carrental.model.UserRole;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private UserRole role;
    private String socialProvider;
    private String socialId;
}
