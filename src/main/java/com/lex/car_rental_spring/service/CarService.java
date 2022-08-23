package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import java.util.List;
import java.util.Optional;

public interface CarService {

public List<Car> listAvailableCars();
public List<Car> listRentedCars();
public List<Car> listAllCars();
public Optional<Car> getCarById(Long id);
}
