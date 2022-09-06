package com.lex.car_rental_spring.controller;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.LocationNotFoundException;
import com.lex.car_rental_spring.serviceImpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationServiceImpl locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        try {
            List<Location> locations = locationService.listAllLocations(pageNo, pageSize, sortBy);
            return new ResponseEntity<>(locations, new HttpHeaders(), HttpStatus.OK);
        }catch (LocationNotFoundException e){
            System.out.println("Nie znaleziono żadnej lokalizacji." + e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public Optional<Location> getLocationById(@PathVariable Long id) {
        try {
            return locationService.getLocationById(id);
        }catch(LocationNotFoundException e){
            System.out.println("Nie znaleziono lokalizacji o podanym id." + e.getMessage());
        }
        return Optional.empty();
    }

    @PostMapping
    public ResponseEntity<Location> newLocation(@RequestBody String city) {
        try {
            locationService.addLocation(city);
        } catch (Throwable e) {
            throw new RuntimeException("Lokalizacja już istnieje." + e.getMessage());
        }
        return new ResponseEntity<>(locationService.getLocationByCity(city), new HttpHeaders(), HttpStatus.CREATED);
    }
}
