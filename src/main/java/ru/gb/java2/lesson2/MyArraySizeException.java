package ru.gb.java2.lesson2;

public class MyArraySizeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Array has wrong size";
    }
}
