package com.lex.car_rental_spring.entity;


import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CarRentalHistory")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @ToString.Include
    private String from_date;
    @ToString.Include
    private String due_date;
    @ToString.Include
    private Integer state_value;
}
