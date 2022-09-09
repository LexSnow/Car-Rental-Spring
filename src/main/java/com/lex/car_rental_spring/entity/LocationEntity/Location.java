package com.lex.car_rental_spring.entity.LocationEntity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_locations")
public class Location {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ToString.Include
    private String city;
    @ToString.Include
    private Double lat;
    @ToString.Include
    private Double lon;
    private String createdBy;
    private LocalTime creationTime = LocalTime.now();

    public Location(String city) {
        this.city = city;
    }
}
