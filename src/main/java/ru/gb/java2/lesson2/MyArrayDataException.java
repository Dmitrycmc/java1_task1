package ru.gb.java2.lesson2;

class MyArrayDataException extends RuntimeException {
    private int i, j;

    MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public String getMessage() {
        return "Element (" + i + "; " + j + ") has wrong format";
    }
}
