package ru.geekbrains.lesson9.rest;

import java.time.LocalDateTime;

public class ErrorDto {
    private String message;
    private LocalDateTime localDataTime = LocalDateTime.now();

    public ErrorDto() {
    }

    public ErrorDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDataTime() {
        return localDataTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
