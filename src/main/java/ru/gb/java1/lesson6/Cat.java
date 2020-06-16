package ru.gb.java1.lesson6;

class Cat extends Animal {
    private static int counter = 0;

    Cat(String name) {
        super(name, 200, 0, 2);

        counter++;
    }

    @Override
    void info() {
        System.out.print("Кот ");
        super.info();
    }

    static void printAmount() {
        System.out.println("Создано котов: " + counter);
    }
}
