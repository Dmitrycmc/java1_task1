package ru.gb.lesson6;

public class Dog extends Animal {
    public Dog(String name) {
        super(name, 500, 10);
    }

    @Override
    public void info() {
        System.out.print("Dog ");
        super.info();
    }
}
