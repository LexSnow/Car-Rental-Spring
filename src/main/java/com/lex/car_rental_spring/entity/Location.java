package com.lex.car_rental_spring.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private Double lat;
    private Double lon;
    private String creator;
    @Column(name = "creation_time")
    @CreationTimestamp
    private Date creationTime;

    public Location(String city) {
        this.city = city;
    }
}
