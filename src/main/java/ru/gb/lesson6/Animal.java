package ru.gb.lesson6;

public class Animal {
    private String name;
    private int runLimit;
    private int swimLimit;

    public Animal(String name, int runLimit, int swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
    }

    public void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " м., т.к. пробегает не более " + runLimit + " м.");
        }
    }

    public void swim(int distance) {
        if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не смог проплыть " + distance + " м., т.к. проплывает не более " + swimLimit + " м.");
        }
    }

    public void info() {
        System.out.println(name);
    }
}

