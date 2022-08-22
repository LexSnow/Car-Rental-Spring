package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.exception.CarNotFoundException;

import java.util.List;

public interface CarService {

public void listAvailableCars();
public void listRentedCars();
public void listCarHistory(Long id);

}
