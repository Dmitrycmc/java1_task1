package ru.gb.java2.lesson2;

public class MyArraySizeException extends RuntimeException {
    MyArraySizeException() {
        super("Array has wrong size");
    }
}
