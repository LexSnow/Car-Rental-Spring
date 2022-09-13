package com.lex.car_rental_spring.entity.DTO;
import com.lex.car_rental_spring.entity.Location;
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
