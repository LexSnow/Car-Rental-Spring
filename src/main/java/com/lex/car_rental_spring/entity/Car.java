package com.lex.car_rental_spring.entity;

import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
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
    private Long id;
    @ToString.Include
    private String city;
    @ToString.Include
    private String brand;
    @ToString.Include
    private String model;
    @Column(name = "car_user_id")
    private String carUserId;
    @Column(name = "from_date")
    private String fromDate;
    @Column(name = "due_date")
    private String dueDate;
    @ToString.Include
    @Column(name = "manufactured_year")
    private Integer manufacturedYear;
    @Column(name = "state_value")
    private Integer stateValue;
    private Boolean rented = false;
    @Column(name = "distance_from_origin")
    private Integer distanceFromOrigin;

    @OneToMany(mappedBy = "car")
    private List<History> rentalHistory;

    public Car(String city, String brand, String model, Integer manufactured_year, Integer state_value){
        this.city = city;
        this.brand = brand;
        this.model = model;
        this.manufacturedYear = manufactured_year;
        this.stateValue = state_value;
    }

    public void setFromDate(String from_date) {
        if(this.dueDate != null){
            this.dueDate = null;
        }
        this.fromDate = from_date;
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
