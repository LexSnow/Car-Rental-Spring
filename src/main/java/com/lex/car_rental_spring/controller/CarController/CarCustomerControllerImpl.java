package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.entity.dto.CarCustomerDTO;
import com.lex.car_rental_spring.entity.mapper.CarMapperImpl;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.serviceImpl.CarServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarCustomerControllerImpl implements CarCustomerController {

    private final CarServiceImpl carService;
    private final CarMapperImpl carMapper;


    @Override
    @GetMapping("/available")
    public ResponseEntity<List<CarCustomerDTO>> availableCars(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "location_id") String sortBy
    ) {
        List<CarCustomerDTO> cars = carMapper.map(carService.listAvailableCars(pageNo, pageSize, sortBy));
        return new ResponseEntity<>(cars, new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<CarCustomerDTO>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                        @RequestParam(defaultValue = "location_id") String sortBy) {
        List<CarCustomerDTO> cars = carMapper.map(carService.listAllCars(pageNo, pageSize, sortBy));
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CarCustomerDTO> getCarById(@PathVariable("id") Long id) {
        CarCustomerDTO car = carMapper.carToCarCustomerDTO(carService.getCarById(id));
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @Override
    @PatchMapping("/rent")
    public ResponseEntity<String> rentCar(@RequestParam Long id) {
        try {
            carService.rentCar(id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body("Samochód jest niedostępny.");
        }
        return ResponseEntity.ok("Samochód został wypożyczony.");
    }

    @Override
    @PatchMapping("/return")
    public ResponseEntity<String> returnCar(@RequestParam Long id, @RequestParam String city) {
        try {
            carService.returnCar(id, city);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body("Podany samochód nie jest aktualnie wypożyczony.");
        }
        return ResponseEntity.ok("Samochód został zwrócony.");
    }
}
