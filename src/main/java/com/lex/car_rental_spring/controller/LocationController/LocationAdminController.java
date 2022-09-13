package com.lex.car_rental_spring.controller.LocationController;

import com.lex.car_rental_spring.entity.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LocationAdminController {
    ResponseEntity<List<Location>> getAllLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    );

    ResponseEntity<Location> getLocationById(@PathVariable Long id);

    ResponseEntity<String> newLocation(@RequestBody String city);

    ResponseEntity<String> deleteLocation(@RequestParam("id") Long id);
}
