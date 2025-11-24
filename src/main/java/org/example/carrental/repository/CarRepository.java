package org.example.carrental.repository;

import org.example.carrental.model.Car;
import org.example.carrental.model.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);

    List<Car> findByModel(String model);

    List<Car> findByStatus(CarStatus status);

    List<Car> findByLocationAndStatus(String location, String status);

    List<Car> findByModelContainingIgnoreCaseAndStatus(String model, String status);
}
