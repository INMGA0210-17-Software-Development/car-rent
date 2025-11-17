package org.example.carrental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public enum UserRole {
    RENTEE,
    RENTER,
    ADMIN;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    private Long id;
}