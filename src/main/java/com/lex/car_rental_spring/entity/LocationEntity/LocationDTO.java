package com.lex.car_rental_spring.entity.LocationEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDTO {
    private Long id;
    private String city;
    private Double lat;
    private Double lon;
}
