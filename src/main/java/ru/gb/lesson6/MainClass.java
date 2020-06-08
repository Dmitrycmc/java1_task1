package ru.gb.lesson6;

public class MainClass {
    public static void main(String[] args) {
        Cat cat = new Cat("Барсик");
        Dog dog = new Dog("Барбос");

        cat.info();
        cat.run(200);
        cat.swim(200);
        dog.info();
        dog.run(200);
        dog.swim(200);
    }
}


