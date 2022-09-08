package com.lex.car_rental_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true, builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_rental")
public class Car {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;
    @ToString.Include
    private String brand;
    @ToString.Include
    private String model;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ToString.Include
    @Column(name = "manufactured_year")
    private Integer manufacturedYear;
    @Column(name = "odometer")
    private Integer odometer;
    private Boolean rented = false;
    @Column(name = "creator")
    private String creator;
    @Column(name = "creation_time")
    private LocalTime creationTime = LocalTime.now();
    @OneToMany(mappedBy = "car")
    private List<History> rentalHistory;

    public Car(Location location, String brand, String model, Integer manufactured_year, Integer odometer) {
        this.location = location;
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufactured_year;
        this.odometer = odometer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
