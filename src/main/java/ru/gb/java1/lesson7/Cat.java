package ru.gb.java1.lesson7;

class Cat {
    private String name;
    private boolean hungry;
    private int appetite;

    Cat(String name, int appetite) {
        this.name = name;
        this.hungry = true;
        this.appetite = appetite;
    }

    void eat(Plate p) {
        hungry = !p.decreaseFood(appetite);
    }

    void printInfo() {
        System.out.println("Cat " + name + " with " + appetite + " appetite is" + (hungry ? " " : " not ") + "hungry");
    }
}
