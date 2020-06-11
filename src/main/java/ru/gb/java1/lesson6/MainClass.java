package ru.gb.java1.lesson6;

public class MainClass {
    public static void main(String[] args) {
        Animal[] animals = new Animal[] {
                new Cat("Барсик"),
                new Dog("Барбос"),
                new Bird("Кеша"),
                new Horse("Гроза")
        };

        Animal.printAmount();
        Bird.printAmount();
        Cat.printAmount();
        Dog.printAmount();
        Horse.printAmount();

        for (Animal animal: animals) {
            System.out.println();
            animal.info();
            animal.run(200);
            animal.swim(200);
            animal.jump(0.6);
        }
    }
}


