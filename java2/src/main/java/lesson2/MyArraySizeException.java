package lesson2;

public class MyArraySizeException extends RuntimeException {
    MyArraySizeException() {
        super("Array has wrong size");
    }
}
