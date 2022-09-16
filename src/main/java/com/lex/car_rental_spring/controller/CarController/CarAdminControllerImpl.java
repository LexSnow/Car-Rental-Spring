package com.lex.car_rental_spring.controller.CarController;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.CarNotFoundException;
import com.lex.car_rental_spring.service.CarServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/cars")
public class CarAdminControllerImpl implements CarAdminController {
    private final CarServiceImpl carService;

    @Override
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Car>> allCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listAllCars(pageNo, pageSize, sortBy));
    }

    @Override
    @GetMapping("/available")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Car>> availableCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listAvailableCars(pageNo, pageSize, sortBy));
    }

    @GetMapping("/rented")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Car>> rentedCars(@RequestParam(defaultValue = "0") Integer pageNo,
                                                @RequestParam(defaultValue = "10") Integer pageSize,
                                                @RequestParam(defaultValue = "location_id") String sortBy) {
        return ResponseEntity.ok(carService.listRentedCars(pageNo, pageSize, sortBy));
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> newCar(@RequestBody Location location,
                                         @RequestBody String brand,
                                         @RequestBody String model,
                                         @RequestBody Integer manufacturedYear,
                                         @RequestBody Integer odometer) {
        carService.saveCar(location, brand, model, manufacturedYear, odometer);
        return ResponseEntity.ok("Samochód został dodany.");
    }

    @Override
    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCar(@RequestParam Long id) {
        try {
            carService.deleteCar(id);
        } catch (CarNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Samochód został usunięty.");
    }
}
