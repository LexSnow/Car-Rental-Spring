package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car,Long>  {
    Page<Car> findByRentedFalse(Pageable pageable) throws CarNotFoundException;
    Page<Car> findByRentedTrue(Pageable pageable) throws CarNotFoundException;
    Car findCarByCity(String city);
}
