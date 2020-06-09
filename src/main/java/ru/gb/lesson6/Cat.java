package ru.gb.lesson6;

public class Cat extends Animal {
    private static int counter = 0;

    public Cat(String name) {
        super(name, 200, 0, 2);
        System.out.println("Создано котов: " + ++counter);
    }

    @Override
    public void info() {
        System.out.print("Кот ");
        super.info();
    }
}
