package com.lex.car_rental_spring.controller.CarController.CarInterfaces;

import com.lex.car_rental_spring.entity.CarEntity.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CarAdminController {
    ResponseEntity<List<Car>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "location_id") String sortBy);

    ResponseEntity<List<Car>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "location_id") String sortBy);
    ResponseEntity<List<Car>> rentedCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "location_id") String sortBy);
    ResponseEntity<Car> getCarById(@PathVariable Long id);

    ResponseEntity<String> newCar(@RequestBody Car car);

    ResponseEntity<String> deleteCar(@RequestParam("id") Long id);
}
