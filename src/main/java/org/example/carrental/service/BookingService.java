package org.example.carrental.service;

import org.example.carrental.dto.BookingRequest;
import org.example.carrental.dto.RentalCostResponse;
import org.example.carrental.model.*;
import org.example.carrental.repository.BookingRepository;
import org.example.carrental.repository.CarRepository;
import org.example.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final PricingService pricingService;

    public BookingService(BookingRepository bookingRepository,
                          CarRepository carRepository,
                          UserRepository userRepository,
                          PricingService pricingService) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.pricingService = pricingService;
    }

    public Booking createBooking(BookingRequest req) {
        User user = userRepository.findById(req.getRenteeId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = carRepository.findById(req.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if (!car.getAvailable()) {
            throw new RuntimeException("Car unavailable");
        }

        RentalCostResponse cost = pricingService.calculateCost(
                car.getId(), req.getStartDate(), req.getEndDate()
        );

        Booking booking = new Booking();
        booking.setRentee(user);
        booking.setCar(car);
        booking.setStartDate(req.getStartDate());
        booking.setEndDate(req.getEndDate());
        booking.setTotalPrice(cost.getTotalPrice());
        booking.setStatus(BookingStatus.CREATED);

        car.setAvailable(false);
        car.setStatus(CarStatus.NOT_AVAILABLE);
        carRepository.save(car);

        return bookingRepository.save(booking);
    }
}
