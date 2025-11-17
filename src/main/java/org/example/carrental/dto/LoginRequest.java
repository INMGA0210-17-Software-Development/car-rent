package org.example.carrental.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String socialProvider;
    private String socialId;
}
