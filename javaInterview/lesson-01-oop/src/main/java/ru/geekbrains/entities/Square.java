package ru.geekbrains.entities;

import ru.geekbrains.interfaces.Figure;

public class Square implements Figure {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getSquare() {
        return side * side;
    }
}
