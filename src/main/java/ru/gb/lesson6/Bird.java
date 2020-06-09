package ru.gb.lesson6;

public class Bird extends Animal {
    private static int counter = 0;

    public Bird(String name) {
        super(name, 5, 0, 0.1);
        System.out.println("Создано птиц: " + ++counter);
    }

    @Override
    public void info() {
        System.out.print("Птица ");
        super.info();
    }
}
