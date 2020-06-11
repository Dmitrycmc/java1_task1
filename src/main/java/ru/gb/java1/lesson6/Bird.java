package ru.gb.java1.lesson6;

public class Bird extends Animal {
    private static int counter = 0;

    public Bird(String name) {
        super(name, 5, 0, 0.1);

        counter++;
    }

    @Override
    public void info() {
        System.out.print("Птица ");
        super.info();
    }

    public static void printAmount() {
        System.out.println("Создано птиц: " + counter);
    }
}