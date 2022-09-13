package com.lex.car_rental_spring.entity.DTO;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class HistoryDTO {
    private Long id;
    private Date fromDate;
    private Date dueDate;
    private Integer startOdometer;
    private Integer endOdometer;
}
