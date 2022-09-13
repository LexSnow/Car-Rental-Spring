package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.CarNotFoundException;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy);
    List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy);
    List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy);
    Car getCarById(Long id);
    void rentCar(Long id) throws CarNotFoundException;

    void returnCar(Long id, String city, Integer endOdometer) throws CarNotFoundException;

    void saveCar(Location location, String brand, String model, Integer manufacturedYear, Integer odometer);

    void patchCar(Long id, Map<String, Object> patch);
    void deleteCar(Long id) throws CarNotFoundException;

}
