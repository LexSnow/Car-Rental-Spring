package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.controller.CarController.CarInterfaces.CarAdminController;
import com.lex.car_rental_spring.entity.CarEntity.Car;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.exception.IncorrectRequestException;
import com.lex.car_rental_spring.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/cars")
public class CarAdminControllerImpl implements CarAdminController {
    private final CarServiceImpl carService;

    @Override
    @GetMapping
    public ResponseEntity<List<Car>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listAllCars(pageNo, pageSize, sortBy));
    }

    @Override
    @GetMapping("/available")
    public ResponseEntity<List<Car>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listAvailableCars(pageNo, pageSize, sortBy));
    }

    @GetMapping("/rented")
    public ResponseEntity<List<Car>> rentedCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listRentedCars(pageNo, pageSize, sortBy));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> newCar(@RequestBody Car car) {
        try {
            carService.saveCar(car);
        } catch (IncorrectRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Samochód został dodany.");
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteCar(@RequestParam Long id) {
        try {
            carService.deleteCar(id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Samochód został usunięty.");
    }
}
