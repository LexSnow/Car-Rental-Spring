package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy);

    List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy);

    List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy);

    Optional<Car> getCarById(Long id);
    void saveCar (Car car);
}
