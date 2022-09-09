package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.LocationEntity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    Location findByCity(String city);
    Boolean existsByCity(String city);
}
