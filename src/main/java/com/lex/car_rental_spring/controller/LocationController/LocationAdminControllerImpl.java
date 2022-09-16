package com.lex.car_rental_spring.controller.LocationController;

import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.LocationNotFoundException;
import com.lex.car_rental_spring.service.LocationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/locations")
public class LocationAdminControllerImpl implements LocationAdminController {
    private final LocationServiceImpl locationService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Location>> getAllLocations(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "city") String sortBy
    ) {
        return ResponseEntity.ok(locationService.listAllLocations(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> newLocation(@RequestBody String city) {
        try {
            locationService.addLocation(city);
            return ResponseEntity.ok("Lokalizacja została dodana do bazy danych.");
        } catch (Throwable e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteLocation(@RequestParam("id") Long id){
        try{
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Lokalizacja została usunięta.");
        } catch (LocationNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
