package com.lex.car_rental_spring.exception;

public class IncorrectRequestException extends Throwable {
    public IncorrectRequestException(String message) {
        super(message);
    }
}
