package com.lex.car_rental_spring.service;


import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.History;

import java.util.List;

public interface CarService {

public void rentCar();
public void returnCar(Car car);
public List<Car> listAvailableCars();
public List<Car> listRentedCars();
public List<History> listCarHistory();

}
