package com.lex.car_rental_spring.serviceImpl;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.repository.CarRepository;
import com.lex.car_rental_spring.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarServiceImpl implements CarService {

    public final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> listAvailableCars(Integer pageNo, Integer pageSize, String sortBy){
        try {
            Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedFalse(paging);
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            System.out.println("Nie ma dostępnych samochodów." + c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listRentedCars(Integer pageNo, Integer pageSize, String sortBy) {
        try {
            Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findByRentedTrue(paging);
            if(!pagedResult.hasContent()){
                throw new CarNotFoundException("Wszystkie samochody są dostępne");
            }
            return pagedResult.getContent();
        } catch (CarNotFoundException c) {
            System.out.println(c.getMessage());
        }
        return null;
    }

    @Override
    public List<Car> listAllCars(Integer pageNo, Integer pageSize, String sortBy){
        try{
            Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
            Page<Car> pagedResult = carRepository.findAll(paging);
            return pagedResult.getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

}
