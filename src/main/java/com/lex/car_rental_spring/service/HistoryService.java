package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.History;

import java.util.Date;
import java.util.List;

public interface HistoryService {
    History getHistory(Long id);
    List<History> listCarHistory(Long id);
    History getHistoryByCarIdAndDueDateIsNull(Long id);

    void updateHistory(History history);
    void deleteHistory(Long id);
}
