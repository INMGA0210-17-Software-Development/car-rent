package org.example.carrental.controller;

import org.example.carrental.dto.BookingRequest;
import org.example.carrental.dto.RentalCostResponse;
import org.example.carrental.model.Booking;
import org.example.carrental.model.Car;
import org.example.carrental.model.User;
import org.example.carrental.repository.BookingRepository;
import org.example.carrental.repository.CarRepository;
import org.example.carrental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable("id") @Param("id") Long id){
        return bookingRepository.findById(id).get();
    }

    @PostMapping("")
    public Booking saveBooking(@RequestBody Booking booking){

        Optional<User> newUser = userRepository.findById(booking.getUser().getId());
        Optional<Car> newCar = carRepository.findById(booking.getCar().getId());
        Booking newBooking = new Booking();

        newBooking.setUser(newUser.get());
        newBooking.setCar(newCar.get());
        newBooking.setStartDate(booking.getStartDate());
        newBooking.setEndDate(booking.getEndDate());
        newBooking.setTotalPrice(booking.getTotalPrice());

        return bookingRepository.save(newBooking);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable("id") @Param("id") Long id, @RequestBody Booking booking){

        Booking newBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Booking not found with id: " + id
                ));

        if (booking.getUser() != null && booking.getUser().getId() != null){
            Optional<User> newUser = userRepository.findById(booking.getUser().getId());
            newBooking.setUser(newUser.get());
        }

        if (booking.getCar() != null && booking.getCar().getId() != null){
            Optional<Car> newCar = carRepository.findById(booking.getCar().getId());
            newBooking.setCar(newCar.get());
        }

        if (booking.getStartDate() != null) newBooking.setStartDate(booking.getStartDate());
        if (booking.getEndDate() != null) newBooking.setEndDate(booking.getEndDate());
        if (booking.getTotalPrice() != null) newBooking.setTotalPrice(booking.getTotalPrice());

        return bookingRepository.save(newBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable("id") @Param("id") Long id){
        bookingRepository.deleteById(id);
    }
}
