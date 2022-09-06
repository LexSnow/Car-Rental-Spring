package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {
    List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy);
    List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy);
    List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy);
    Optional<Car> getCarById(Long id);
    List<Car> listCarsByLocation(Integer pageNo, Integer pageSize, String sortBy, Location location);
    void saveCar(Car car);
    void patchCar(Long id, Map<String, Object> patch);
    void deleteCar(Long id);
    List<Car> listNearestCars(String city);
}
