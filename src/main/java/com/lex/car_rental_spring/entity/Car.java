package com.lex.car_rental_spring.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true, builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_rental")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    private String brand;

    private String model;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "manufactured_year")
    private Integer manufacturedYear;
    private Integer odometer;
    private Boolean rented = false;
    private String creator;
    @Column(name = "creation_time")
    @CreationTimestamp
    private Date creationTime;
    @Column(name = "modification_time")
    @UpdateTimestamp
    private Date modificationTime;
    @OneToMany(mappedBy = "car")
    private List<History> rentalHistory;

    public Car(Location location, String brand, String model, Integer manufactured_year, Integer odometer) {
        this.location = location;
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufactured_year;
        this.odometer = odometer;
        this.rentalHistory = new ArrayList<>();
    }
    public void addToHistory(History history){
        rentalHistory.add(history);
    }
}
