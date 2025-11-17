package org.example.carrental.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private Long renteeId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
}
