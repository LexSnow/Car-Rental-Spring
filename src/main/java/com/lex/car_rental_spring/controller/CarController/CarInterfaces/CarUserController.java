package com.lex.car_rental_spring.controller.CarController.CarInterfaces;

import com.lex.car_rental_spring.entity.CarEntity.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CarUserController {
    ResponseEntity<String> rentCar(Long id);

    ResponseEntity<String> returnCar(Long id, String city);

    ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id);

    ResponseEntity<List<CarDTO>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "location_id") String sortBy);

    ResponseEntity<List<CarDTO>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "location_id") String sortBy);


}
