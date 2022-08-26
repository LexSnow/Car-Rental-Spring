package com.lex.car_rental_spring.entity.mapper;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDTO carToCarDTO(Car car);
    Car carDTOtoCar(CarDTO carDTO);
}
