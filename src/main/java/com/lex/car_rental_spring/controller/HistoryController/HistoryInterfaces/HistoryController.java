package com.lex.car_rental_spring.controller.HistoryController.HistoryInterfaces;

import com.lex.car_rental_spring.entity.HistoryEntity.History;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface HistoryController {
    ResponseEntity<List<History>> listCarHistory(Long id);

    ResponseEntity<String> createHistory(@RequestBody Long car_id,
                                         @RequestBody Date fromDate,
                                         @RequestBody Integer startOdometer);

    ResponseEntity<String> updateHistory(@RequestBody Long id,
                                         @RequestBody Date dueDate,
                                         @RequestBody Integer endOdometer);
    ResponseEntity<String> deleteHistory(Long id);
}
