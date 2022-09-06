package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.Location;
import com.lex.car_rental_spring.exception.LocationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listAllLocations(Integer pageNo, Integer pageSize, String sortBy) throws LocationNotFoundException;
    Location getLocationByCity(String city);
    Optional<Location> getLocationById(Long id) throws LocationNotFoundException;
    void addLocation(String city) throws Throwable;
}

