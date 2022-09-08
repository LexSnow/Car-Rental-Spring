package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.entity.dto.CarCustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CarCustomerController {
    ResponseEntity<String> rentCar(Long id);

    ResponseEntity<String> returnCar(Long id, String city);

    ResponseEntity<CarCustomerDTO> getCarById(@PathVariable("id") Long id);

    ResponseEntity<List<CarCustomerDTO>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                                       @RequestParam(defaultValue = "location_id") String sortBy);

    ResponseEntity<List<CarCustomerDTO>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "location_id") String sortBy);


}
