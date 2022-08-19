package com.lex.car_rental_spring.entity;

import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CarRental")
public class Car {
    @ToString.Include
    @Id
    private Long id;
    @ToString.Include
    private String city;
    @ToString.Include
    private String brand;
    @ToString.Include
    private String model;
    private String car_user_id;
    private String from_date;
    private String due_date;
    @ToString.Include
    private Integer manufactured_year;
    private Integer state_value;
    private Boolean rented;
    private Integer distance_from_origin;

    @OneToMany
    @JoinColumn(name = "history_id")
    private List<History> rentalHistory;

    public Car(String city, String brand, String model, Integer manufactured_year, Integer state_value){
        this.city = city;
        this.brand = brand;
        this.model = model;
        this.manufactured_year = manufactured_year;
        this.state_value = state_value;
    }

    public void setFrom_date(String from_date) {
        if(this.due_date != null){
            this.due_date = null;
        }
        this.from_date = from_date;
    }

    public void setRented() {
        this.rented = !this.rented;
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
