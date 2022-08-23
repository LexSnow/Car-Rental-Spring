package com.lex.car_rental_spring.serviceImpl;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.repository.CarRepository;
import com.lex.car_rental_spring.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    public CarRepository carRepository;

    @Override
    public List<Car> listAvailableCars() {
        try {
            return carRepository.findByRentedFalse(Sort.by(Sort.Direction.DESC, "city").and(Sort.by(Sort.Direction.DESC, "manufactured_year")));
        } catch (CarNotFoundException c) {
            System.out.println("Nie ma dostępnych samochodów." + c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listRentedCars() {
        try {
            return carRepository.findByRentedTrue(Sort.by(Sort.Direction.DESC, "city").and(Sort.by(Sort.Direction.DESC, "manufactured_year")));
        } catch (CarNotFoundException c) {
            System.out.println("Wszystkie samochody są dostępne." + c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listAllCars(){
        return carRepository.findAll(Sort.by(Sort.Direction.DESC, "city").and(Sort.by(Sort.Direction.DESC, "manufactured_year")));
    }

    @Override
    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

}
