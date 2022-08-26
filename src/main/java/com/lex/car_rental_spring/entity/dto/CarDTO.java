package com.lex.car_rental_spring.entity.dto;
import lombok.*;
@Data
@Builder
public class CarDTO {
    private Integer stateValue;
    private Boolean rented;
    private Integer distanceFromOrigin;
    private Long carUserId;
    private String fromDate;
    private String dueDate;
}
