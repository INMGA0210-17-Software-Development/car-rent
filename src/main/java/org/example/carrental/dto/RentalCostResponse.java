package org.example.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalCostResponse {
    private Long carId;
    private long days;
    private Double dailyPrice;
    private Double totalPrice;
}
