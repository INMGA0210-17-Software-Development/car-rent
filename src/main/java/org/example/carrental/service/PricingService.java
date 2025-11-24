package org.example.carrental.service;
/*
import org.example.carrental.dto.RentalCostResponse;
import org.example.carrental.model.Car;
import org.example.carrental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PricingService {

    private final CarRepository carRepository;

    public PricingService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public RentalCostResponse calculateCost(Long carId, LocalDate start, LocalDate end) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        long days = ChronoUnit.DAYS.between(start, end);
        if (days <= 0) throw new RuntimeException("End date must be after start date");

        double total = days * car.getDailyPrice();
        return new RentalCostResponse(carId, days, car.getDailyPrice(), total);
    }
}
*/