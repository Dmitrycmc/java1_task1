package ru.gb.lesson6;

public class Horse extends Animal {
    private static int counter = 0;

    public Horse(String name) {
        super(name, 1500, 100, 3);
        System.out.println("Создано лошадей: " + ++counter);
    }

    @Override
    public void info() {
        System.out.print("Лошадь ");
        super.info();
    }
}
