package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Long>  {
    Page<Car> findByRentedFalse(Pageable pageable) throws CarNotFoundException;
    List<Car> findByRentedFalse();
    Page<Car> findByRentedTrue(Pageable pageable) throws CarNotFoundException;
    Page<Car> findCarsByLocation(Pageable pageable, Location location) throws CarNotFoundException;

}
