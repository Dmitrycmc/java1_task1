package ru.gb.lesson7;

public class Cat {
    private String name;
    private boolean hungry;
    private int appetite;

    public Cat(String name, int appetite) {
        this.name = name;
        this.hungry = true;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
        hungry = !p.decreaseFood(appetite);
    }

    public void printInfo() {
        System.out.println("Cat " + name + " with " + appetite + " appetite is" + (hungry ? " " : " not ") + "hungry");
    }
}
