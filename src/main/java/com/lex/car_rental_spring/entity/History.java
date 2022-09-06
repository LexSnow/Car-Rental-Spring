package com.lex.car_rental_spring.entity;


import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_rental_history")
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
    private String from_date;
    private String due_date;
    private Integer state_value;
}
