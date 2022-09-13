package com.lex.car_rental_spring.entity.mapper;

import com.lex.car_rental_spring.entity.DTO.HistoryDTO;
import com.lex.car_rental_spring.entity.History;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper
public interface HistoryMapper {
    HistoryMapper INSTANCE = Mappers.getMapper(HistoryMapper.class);
    HistoryDTO historyToHistoryDTO(History history);
    History HistoryDTOtoHistory(HistoryDTO historyDTO);
    List<HistoryDTO> map (List<History> history);
}
