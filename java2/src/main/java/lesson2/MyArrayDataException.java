package lesson2;

class MyArrayDataException extends RuntimeException {
    MyArrayDataException(int i, int j) {
        super("Element (" + i + "; " + j + ") has wrong format");
    }
}
