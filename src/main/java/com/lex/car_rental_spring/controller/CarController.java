package com.lex.car_rental_spring.controller;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.serviceImpl.CarServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listAllCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/cars/rented")
    public ResponseEntity<List<Car>> getRentedCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listRentedCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/cars/available")
    public ResponseEntity<List<Car>> getAvailableCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listAvailableCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }
}



