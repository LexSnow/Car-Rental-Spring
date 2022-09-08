package com.lex.car_rental_spring.entity.mapper;

import com.lex.car_rental_spring.entity.Car;
import com.lex.car_rental_spring.entity.dto.CarCustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);
    CarCustomerDTO carToCarCustomerDTO(Car car);
    Car carCustomerDTOtoCar(CarCustomerDTO carCustomerDTO);
    List<CarCustomerDTO> map (List<Car> cars);
}
