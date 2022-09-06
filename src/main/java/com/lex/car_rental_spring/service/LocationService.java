package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listAllLocations(Integer pageNo, Integer pageSize, String sortBy);
    Location getLocationByCity(String city);
    Optional<Location> getLocationById(Long id);
    void addLocation(String city) throws Throwable;
}

