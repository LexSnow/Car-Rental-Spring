package com.lex.car_rental_spring.serviceImpl;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.repository.CarRepository;
import com.lex.car_rental_spring.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    @Autowired
    public CarRepository carRepository;

    @Override
    public List<Car> listAvailableCars() {
        try {
            return carRepository.findByRentedFalse().stream().sorted(Comparator.comparing(Car::getCity).thenComparingInt(Car::getManufactured_year)).collect(Collectors.toList());
        } catch (CarNotFoundException c) {
            System.out.println("Nie ma dostępnych samochodów." + c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listRentedCars() {
        try {
            return carRepository.findByRentedTrue().stream().sorted(Comparator.comparing(Car::getCity).thenComparingInt(Car::getManufactured_year)).collect(Collectors.toList());
        } catch (CarNotFoundException c) {
            System.out.println("Wszystkie samochody są dostępne." + c.getMessage());
        }
        return null;
    }
}
