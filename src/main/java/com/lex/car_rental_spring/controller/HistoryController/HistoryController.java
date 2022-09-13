package com.lex.car_rental_spring.controller.HistoryController;

import com.lex.car_rental_spring.entity.DTO.HistoryDTO;
import com.lex.car_rental_spring.entity.History;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface HistoryController {
    @GetMapping("/{id}")
    ResponseEntity<List<HistoryDTO>> listCarHistory(@PathVariable Long id);

    @PutMapping
    ResponseEntity<String> updateHistory(@RequestBody History history);
    @DeleteMapping
    ResponseEntity<String> deleteHistory(Long id);
}
