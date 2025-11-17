package org.example.carrental.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String socialProvider;
    private String socialId;
}
