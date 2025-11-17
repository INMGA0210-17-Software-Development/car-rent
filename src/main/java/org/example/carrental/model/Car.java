package org.example.carrental.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String plateNumber;

    private String location;

    private Double dailyPrice;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private Boolean available;
}
