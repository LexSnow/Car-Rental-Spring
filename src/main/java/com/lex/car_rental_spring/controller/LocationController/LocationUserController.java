package com.lex.car_rental_spring.controller.LocationController;

import com.lex.car_rental_spring.entity.DTO.LocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LocationUserController {
    ResponseEntity<List<LocationDTO>> getAllLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    );
    ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id);
}
