package ru.gb.java1.lesson6;

class Bird extends Animal {
    private static int counter = 0;

    Bird(String name) {
        super(name, 5, 0, 0.1);

        counter++;
    }

    @Override
    void info() {
        System.out.print("Птица ");
        super.info();
    }

    static void printAmount() {
        System.out.println("Создано птиц: " + counter);
    }
}