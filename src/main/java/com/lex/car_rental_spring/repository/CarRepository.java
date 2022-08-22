package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long>  {
    public List<Car> findByRentedFalse() throws CarNotFoundException;
    public List<Car> findByRentedTrue() throws CarNotFoundException;
    public Car findCarByCity(String city);
}
