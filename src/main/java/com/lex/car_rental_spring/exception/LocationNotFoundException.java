package com.lex.car_rental_spring.exception;

public class LocationNotFoundException extends Throwable{
    public LocationNotFoundException(String message) {
        super(message);
    }
}
