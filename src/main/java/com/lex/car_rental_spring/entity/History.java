package com.lex.car_rental_spring.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder(toBuilder = true, builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_rental_history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    @Column(name = "from_date")
    @CreationTimestamp
    private Date fromDate;
    private Date dueDate;
    @Column(name = "start_odometer")
    private Integer startOdometer;
    @Column(name = "end_odometer")
    private Integer endOdometer;

    public History(Integer startOdometer) {
        this.startOdometer = startOdometer;
    }
}
