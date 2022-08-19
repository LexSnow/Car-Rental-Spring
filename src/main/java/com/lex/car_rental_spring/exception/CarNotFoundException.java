package com.lex.car_rental_spring.exception;

public class CarNotFoundException extends Throwable{
    public CarNotFoundException(String message) {
        super(message);
    }
}
