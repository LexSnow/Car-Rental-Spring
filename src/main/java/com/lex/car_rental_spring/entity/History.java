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
    @Column(name = "from_date")
    private String fromDate;
    @Column(name = "due_date")
    private String dueDate;
    private Integer startOdometer;
    private Integer endOdometer;
}
