package com.lex.car_rental_spring.entity.CarEntity;
import com.lex.car_rental_spring.entity.LocationEntity.Location;
import lombok.*;
@Data
@Builder
public class CarDTO {
    private Location location;
    private String brand;
    private String model;
    private Integer manufacturedYear;
    private Boolean rented;
}
