package com.lex.car_rental_spring.controller.LocationController;

import com.lex.car_rental_spring.controller.LocationController.LocationInterfaces.LocationUserController;
import com.lex.car_rental_spring.entity.LocationEntity.Location;
import com.lex.car_rental_spring.entity.LocationEntity.LocationDTO;
import com.lex.car_rental_spring.entity.LocationEntity.LocationMapper;
import com.lex.car_rental_spring.exception.LocationNotFoundException;
import com.lex.car_rental_spring.service.LocationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/locations")
public class LocationUserControllerImpl implements LocationUserController {
    private final LocationServiceImpl locationService;
    private final LocationMapper locationMapper;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        try {
            List<Location> locations = locationService.listAllLocations(pageNo, pageSize, sortBy);
            return new ResponseEntity<>(locationMapper.map(locations), HttpStatus.OK);
        } catch (LocationNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        return new ResponseEntity<>(locationMapper.locationToLocationDTO(locationService.getLocationById(id)), HttpStatus.OK);
    }
}
