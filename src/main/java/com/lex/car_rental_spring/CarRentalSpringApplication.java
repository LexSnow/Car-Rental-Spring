package com.lex.car_rental_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lex.*"})
public class CarRentalSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalSpringApplication.class, args);
    }
}
