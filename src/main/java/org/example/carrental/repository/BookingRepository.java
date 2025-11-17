package org.example.carrental.repository;

import org.example.carrental.model.Booking;
import org.example.carrental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRentee(User rentee);
}
