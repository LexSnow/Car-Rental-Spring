package com.lex.car_rental_spring.service.ServiceInterfaces;

import com.lex.car_rental_spring.entity.HistoryEntity.History;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    History getHistory(Long id);
    History getHistoryByCarIdAndDueDate(Long id, Date dueDate);
    List<History> listCarHistory(Long id);
    void createHistory(Long car_id, Date fromDate, Integer startOdometer);
    void updateHistory(Long id, Date dueDate, Integer endOdometer);
    void deleteHistory(Long id);
}
