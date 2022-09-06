package com.lex.car_rental_spring.controller;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.serviceImpl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listAllCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Car>> getRentedCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listRentedCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        List<Car> cars = carService.listAvailableCars(pageNo, pageSize, sortBy);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{city}")
    public ResponseEntity<List<Car>> getCarByCity(@PathVariable("city") Location location,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "brand") String sortBy) {
        List<Car> cars = carService.listCarsByLocation(pageNo, pageSize, sortBy, location);
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/nearest/{city}")
    public ResponseEntity<List<Car>> getNearestCars(@PathVariable("city") String city){
        List<Car> nearestCars = carService.listNearestCars(city);
        return new ResponseEntity<>(nearestCars, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Car> newCar(@RequestBody Car car) {
        carService.saveCar(car);
        return new ResponseEntity<>(car, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        carService.saveCar(car);
        return new ResponseEntity<>(car, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Optional<Car>> patchCar(@PathVariable("id") Long id, @RequestBody Map<String, Object> patch) {
        carService.patchCar(id, patch);
        Optional<Car> car = carService.getCarById(id);
        return new ResponseEntity<>(car, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }
}



