package ru.gb.lesson6;

public class Dog extends Animal {
    private static int counter = 0;

    public Dog(String name) {
        super(name, 500, 10);
        System.out.println("Создано собак: " + ++counter);
    }

    @Override
    public void info() {
        System.out.print("Пес ");
        super.info();
    }
}
