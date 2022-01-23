package ru.geekbrains.lesson7_2.controller;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
