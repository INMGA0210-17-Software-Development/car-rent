package org.example.carrental.controller;

import org.example.carrental.dto.CarSearchRequest;
import org.example.carrental.model.Car;
import org.example.carrental.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @PostMapping("/search")
    public List<Car> search(@RequestBody CarSearchRequest request) {
        return carService.searchCars(request);
    }
}
