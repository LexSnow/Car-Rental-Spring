package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.DTO.HistoryDTO;
import com.lex.car_rental_spring.entity.History;
import com.lex.car_rental_spring.entity.mapper.HistoryMapper;
import com.lex.car_rental_spring.exception.HistoryNotFoundException;
import com.lex.car_rental_spring.repository.HistoryRepository;
import com.vaadin.flow.router.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;


    @Override
    public History getHistory(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono historii o podanym id."));
    }

    @Override
    public List<HistoryDTO> listCarHistory(Long id) {
        try {
            List<History> history = historyRepository.findByCarId(id);
            return historyMapper.map(history);
        } catch (HistoryNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public History getHistoryByCarIdAndDueDateIsNull(Long id) {
        try {
            return historyRepository.findByCarIdAndDueDate(id, null);
        } catch (HistoryNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateHistory(History history) {
        historyRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
}
