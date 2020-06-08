package ru.gb.lesson7;

public class MainClass {
    public static void main(String[] args) {

        Cat[] cats = new Cat[] {
                new Cat("Барсик", 5),
                new Cat("Рыжик", 20),
                new Cat("Степа", 15),
                new Cat("Мурзик", 25),
        };

        Plate plate = new Plate(50);
        plate.printInfo();

        for (Cat cat: cats) {
            cat.eat(plate);
            cat.printInfo();
            plate.printInfo();
        }

        plate.increaseFood(15);
        plate.printInfo();

        cats[3].eat(plate);
        cats[3].printInfo();
        plate.printInfo();
    }
}


