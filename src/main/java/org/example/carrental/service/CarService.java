package org.example.carrental.service;

import org.example.carrental.dto.CarSearchRequest;
import org.example.carrental.model.Car;
import org.example.carrental.model.CarStatus;
import org.example.carrental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/*
@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        car.setStatus(CarStatus.AVAILABLE);
        car.setAvailable(true);
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car update) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        car.setBrand(update.getBrand());
        car.setModel(update.getModel());
        car.setPlateNumber(update.getPlateNumber());
        car.setLocation(update.getLocation());
        car.setDailyPrice(update.getDailyPrice());

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public List<Car> searchCars(CarSearchRequest req) {
        List<Car> cars = carRepository.findByStatusAndAvailableTrue(CarStatus.AVAILABLE);

        if (req.getLocation() != null) {
            cars.removeIf(c -> !c.getLocation().equalsIgnoreCase(req.getLocation()));
        }

        if (req.getBrand() != null) {
            cars.removeIf(c -> !c.getBrand().toLowerCase().contains(req.getBrand().toLowerCase()));
        }

        if ("priceAsc".equalsIgnoreCase(req.getSortBy())) {
            cars.sort(Comparator.comparing(Car::getDailyPrice));
        }

        if ("priceDesc".equalsIgnoreCase(req.getSortBy())) {
            cars.sort(Comparator.comparing(Car::getDailyPrice).reversed());
        }

        return cars;
    }
}
*/