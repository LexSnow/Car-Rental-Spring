package com.lex.car_rental_spring.entity.HistoryEntity;


import com.lex.car_rental_spring.entity.CarEntity.Car;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    @CreationTimestamp
    private Date fromDate;
    @UpdateTimestamp
    private Date dueDate;
    @Column(name = "start_odometer")
    private Integer startOdometer;
    @Column(name = "end_odometer")
    private Integer endOdometer;

    public History(Date fromDate, Integer startOdometer) {
        this.fromDate = fromDate;
        this.startOdometer = startOdometer;
    }
}
