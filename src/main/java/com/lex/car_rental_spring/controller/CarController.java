package com.lex.car_rental_spring.controller;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.serviceImpl.CarServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    public final CarServiceImpl carService;

    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.listAllCars();
    }

    @GetMapping("/cars/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/cars/rented")
    public List<Car> getRentedCars() {
        return carService.listRentedCars();
    }

    @GetMapping("/cars/available")
    public List<Car> getAvailableCars() {
        return carService.listAvailableCars();
    }
}



