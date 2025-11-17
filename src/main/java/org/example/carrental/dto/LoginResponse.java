package org.example.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.carrental.model.UserRole;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private String email;
    private String name;
    private UserRole role;
    private String message;
}
