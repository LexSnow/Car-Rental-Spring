package com.lex.car_rental_spring.service;

import com.lex.car_rental_spring.entity.CarEntity.Car;
import com.lex.car_rental_spring.entity.HistoryEntity.History;
import com.lex.car_rental_spring.exception.HistoryNotFoundException;
import com.lex.car_rental_spring.repository.HistoryRepository;
import com.lex.car_rental_spring.service.ServiceInterfaces.HistoryService;
import com.vaadin.flow.router.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final CarServiceImpl carService;

    @Override
    public History getHistory(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono historii o podanym id."));
    }

    @Override
    public List<History> listCarHistory(Long id) {
        try {
            return historyRepository.findByCarId(id);
        } catch (HistoryNotFoundException e) {
            throw new RuntimeException("Historia tego samochodu jest pusta." + e.getMessage());
        }
    }

    @Override
    public History getHistoryByCarIdAndDueDate(Long id, Date dueDate) {
        try {
            return historyRepository.findByCarIdAndDueDate(id, dueDate);
        }catch(HistoryNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void createHistory(Long car_id, Date fromDate, Integer startOdometer) {
        Car car = carService.getCarById(car_id);
        History history = new History(car, fromDate, startOdometer);
    }

    @Override
    public void updateHistory(Long id, Date dueDate, Integer endOdometer) {
        History history = getHistory(id);
        history.setDueDate(dueDate);
        history.setEndOdometer(endOdometer);
        historyRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
}
