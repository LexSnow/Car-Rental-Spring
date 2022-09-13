package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.entity.DTO.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CarUserController {
    ResponseEntity<String> rentCar(Long id);

    ResponseEntity<String> returnCar(@RequestParam("id") Long id,
                                     @RequestParam("city") String city,
                                     @RequestParam Integer endOdometer);

    ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id);

    ResponseEntity<List<CarDTO>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               @RequestParam(defaultValue = "location_id") String sortBy);

    ResponseEntity<List<CarDTO>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize,
                                         @RequestParam(defaultValue = "location_id") String sortBy);


}
