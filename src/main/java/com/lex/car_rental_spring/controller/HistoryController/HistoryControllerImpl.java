package com.lex.car_rental_spring.controller.HistoryController;

import com.lex.car_rental_spring.entity.DTO.HistoryDTO;
import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.service.HistoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryControllerImpl implements HistoryController {
    private final HistoryServiceImpl historyService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<List<HistoryDTO>> listCarHistory(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.listCarHistory(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> updateHistory(@RequestBody History history) {

        historyService.updateHistory(history);
        return ResponseEntity.ok("Pomyślnie zaktualizowano historię.");
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteHistory(Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.ok("Wpis w historii został usunięty.");
    }
}
