package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.CarEntity.Car;
import com.lex.car_rental_spring.entity.HistoryEntity.History;
import com.lex.car_rental_spring.exception.HistoryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
List<History> findByCarId(Long id) throws HistoryNotFoundException;
History findByCarIdAndDueDate(Long id, Date dueDate) throws  HistoryNotFoundException;
}
