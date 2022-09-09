package com.lex.car_rental_spring.entity.CarEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarDTO carToCarCustomerDTO(Car car);
    Car carDTOtoCar(CarDTO carDTO);
    List<CarDTO> map (List<Car> cars);
}
