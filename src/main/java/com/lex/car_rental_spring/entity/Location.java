package com.lex.car_rental_spring.entity;

import lombok.*;

import javax.persistence.*;

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

    public Location(String city) {
        this.city = city;
    }
}
