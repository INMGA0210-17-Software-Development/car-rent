package org.example.carrental.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rentee_id")
    private User rentee;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private LocalDate startDate;
    private LocalDate endDate;

    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
