package org.example.carrental.controller;

import org.example.carrental.dto.CarSearchRequest;
import org.example.carrental.model.Car;
import org.example.carrental.model.User;
import org.example.carrental.repository.CarRepository;
import org.example.carrental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Car getCar(@PathVariable("id") @Param("id") Long id){
        return carRepository.findById(id).get();
    }

    @GetMapping("")
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @PostMapping("")
    public Car saveCar(@RequestBody Car car){

        Optional<User> user = userRepository.findById(car.getUser().getId());
        Car newCar = new Car();

        newCar.setUser(user.get());
        newCar.setModel(car.getModel());
        newCar.setYear(car.getYear());
        newCar.setFeatures(car.getFeatures());
        newCar.setPhoto(car.getPhoto());
        newCar.setCarPrice(car.getCarPrice());
        newCar.setStatus(car.getStatus());
        newCar.setLocation(car.getLocation());

        return carRepository.save(newCar);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable("id") @Param("id") Long id, @RequestBody Car car){

        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Car not found with id: " + id
                ));

        if (car.getUser().getId() != null){
            Optional<User> user = userRepository.findById(car.getUser().getId());
            existingCar.setUser(user.get());
        }

        if (car.getModel() != null) existingCar.setModel(car.getModel());
        existingCar.setYear(car.getYear());
        if (car.getFeatures() != null) existingCar.setFeatures(car.getFeatures());
        if (car.getPhoto() != null) existingCar.setPhoto(car.getPhoto());
        existingCar.setCarPrice(car.getCarPrice());
        if (car.getStatus() != null) existingCar.setStatus(car.getStatus());
        if (car.getLocation() != null) existingCar.setLocation(car.getLocation());

        return carRepository.save(existingCar);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") @Param("id") Long id){
        carRepository.deleteById(id);
    }
}
