package org.example.carrental.repository;

import org.example.carrental.model.Car;
import org.example.carrental.model.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByStatusAndAvailableTrue(CarStatus status);

    List<Car> findByLocationAndAvailableTrue(String location);

    List<Car> findByBrandContainingIgnoreCaseAndAvailableTrue(String brand);
}
