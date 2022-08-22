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
    public void listAvailableCars() {
        try {
            carRepository.findByRentedFalse().stream().sorted(Comparator.comparing(Car::getCity).thenComparingInt(Car::getManufactured_year)).forEach(System.out::println);
        } catch (CarNotFoundException c) {
            System.out.println("Nie ma dostępnych samochodów." + c.getMessage());
        }
    }


    @Override
    public void listCarHistory(Long id){
        try{
            carRepository.findById(id).stream().map(Car::getRentalHistory).collect(Collectors.toList()).forEach(System.out::println);
        } catch(Throwable t) {
            if(!carRepository.existsById(id)){
                System.out.println("Nie ma samochodu o takim id." + t.getMessage());
            } else {
                System.out.println("Historia samochodu jest pusta." + t.getMessage());
            }
        }
    }

    @Override
    public void listRentedCars() {
        try {
            carRepository.findByRentedTrue().stream().sorted(Comparator.comparing(Car::getCity).thenComparingInt(Car::getManufactured_year)).forEach(System.out::println);
        } catch (CarNotFoundException c) {
            System.out.println("Wszystkie samochody są dostępne." + c.getMessage());
        }

    }
}
