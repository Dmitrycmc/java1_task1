package ru.gb.java1.lesson6;

public class Cat extends Animal {
    private static int counter = 0;

    public Cat(String name) {
        super(name, 200, 0, 2);

        counter++;
    }

    @Override
    public void info() {
        System.out.print("Кот ");
        super.info();
    }

    public static void printAmount() {
        System.out.println("Создано котов: " + counter);
    }
}
