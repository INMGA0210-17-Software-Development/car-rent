package org.example.carrental.controller;

import org.example.carrental.dto.BookingRequest;
import org.example.carrental.dto.RentalCostResponse;
import org.example.carrental.model.Booking;
import org.example.carrental.service.BookingService;
import org.example.carrental.service.PricingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PricingService pricingService;

    public BookingController(BookingService bookingService,
                             PricingService pricingService) {
        this.bookingService = bookingService;
        this.pricingService = pricingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest req) {
        return bookingService.createBooking(req);
    }

    @GetMapping("/cost")
    public RentalCostResponse cost(@RequestParam Long carId,
                                   @RequestParam String start,
                                   @RequestParam String end) {

        return pricingService.calculateCost(
                carId,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}
