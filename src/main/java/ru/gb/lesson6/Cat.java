package ru.gb.lesson6;

public class Cat extends Animal {
    public Cat(String name) {
        super(name, 200, 0);
    }

    @Override
    public void info() {
        System.out.print("Cat ");
        super.info();
    }
}
