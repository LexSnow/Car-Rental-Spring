package com.lex.car_rental_spring.entity.mapper;

import com.lex.car_rental_spring.entity.DTO.LocationDTO;
import com.lex.car_rental_spring.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);
    LocationDTO locationToLocationDTO(Location location);
    Location locationDTOtoLocation(LocationDTO locationDTO);
    List<LocationDTO> map (List<Location> locations);
}
