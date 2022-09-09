package com.lex.car_rental_spring.repository;

import com.lex.car_rental_spring.entity.HistoryEntity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {

}
