package com.example.demo.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String nieZnalazlemUzytkownika) {
        super(nieZnalazlemUzytkownika);
    }
}
