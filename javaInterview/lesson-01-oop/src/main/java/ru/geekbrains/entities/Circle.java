package ru.geekbrains.entities;

import ru.geekbrains.interfaces.Figure;

public class Circle implements Figure {
    private double r;

    public Circle(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }

    @Override
    public double getSquare() {
        return Math.PI * r * r;
    }
}
