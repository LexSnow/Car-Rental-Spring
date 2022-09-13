package com.lex.car_rental_spring.controller.HistoryController;

import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.service.CarServiceImpl;
import com.lex.car_rental_spring.service.HistoryService;
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

    @GetMapping
    public ResponseEntity<List<History>> listAll() {
        return ResponseEntity.ok(historyService.listAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<List<History>> listCarHistory(@PathVariable Long id) {
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
