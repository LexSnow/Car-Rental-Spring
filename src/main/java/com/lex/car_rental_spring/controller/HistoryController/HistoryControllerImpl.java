package com.lex.car_rental_spring.controller.HistoryController;

import com.lex.car_rental_spring.controller.HistoryController.HistoryInterfaces.HistoryController;
import com.lex.car_rental_spring.entity.HistoryEntity.History;
import com.lex.car_rental_spring.service.ServiceInterfaces.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryControllerImpl implements HistoryController {
    private final HistoryService historyService;
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<List<History>> listCarHistory(@PathVariable Long id) {
        return ResponseEntity.ok(historyService.listCarHistory(id));
    }

    @Override
    @PutMapping
    public ResponseEntity<String> updateHistory(@RequestBody Long id,
                                                @RequestBody Date dueDate,
                                                @RequestBody Integer endOdometer) {
        historyService.updateHistory(id, dueDate, endOdometer);
        return ResponseEntity.ok("Pomyślnie zaktualizowano historię.");
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteHistory(Long id) {
        historyService.deleteHistory(id);
        return ResponseEntity.ok("Wpis w historii został usunięty.");
    }
}
