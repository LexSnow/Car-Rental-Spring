package com.lex.car_rental_spring.service.ServiceInterfaces;

import com.lex.car_rental_spring.entity.LocationEntity.Location;
import com.lex.car_rental_spring.exception.LocationNotFoundException;

import java.util.List;

public interface LocationService {
    List<Location> listAllLocations(Integer pageNo, Integer pageSize, String sortBy) throws LocationNotFoundException;
    Location getLocationByCity(String city);
    Location getLocationById(Long id) throws LocationNotFoundException;
    void addLocation(String city) throws Throwable;
    void deleteLocation(Long id) throws LocationNotFoundException;
}

