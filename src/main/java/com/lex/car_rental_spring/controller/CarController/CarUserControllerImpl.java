package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.controller.CarController.CarInterfaces.CarUserController;
import com.lex.car_rental_spring.entity.CarEntity.CarDTO;
import com.lex.car_rental_spring.entity.CarEntity.CarMapperImpl;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarUserControllerImpl implements CarUserController {

    private final CarServiceImpl carService;
    private final CarMapperImpl carMapper;


    @Override
    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> availableCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "location_id") String sortBy
    ) {
        List<CarDTO> cars = carMapper.map(carService.listAvailableCars(pageNo, pageSize, sortBy));
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "location_id") String sortBy) {
        List<CarDTO> cars = carMapper.map(carService.listAllCars(pageNo, pageSize, sortBy));
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable("id") Long id) {
        CarDTO car = carMapper.carToCarCustomerDTO(carService.getCarById(id));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/rent")
    public ResponseEntity<String> rentCar(@RequestParam("id") Long id) {
        try {
            carService.rentCar(id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Samochód został wypożyczony.");
    }

    @Override
    @PatchMapping("/return")
    public ResponseEntity<String> returnCar(@RequestParam("id") Long id, @RequestParam("city") String city) {
        try {
            carService.returnCar(id, city);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Samochód został zwrócony.");
    }
}