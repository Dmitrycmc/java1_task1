package ru.geekbrains.entities;

import ru.geekbrains.interfaces.Figure;

public class Triangle implements Figure {
    private double side1;
    private double side2;
    private double angle;

    public Triangle(double side1, double side2, double angle) {
        this.side1 = side1;
        this.side2 = side2;
        this.angle = angle;
    }

    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public double getSquare() {
        return side1 * side2 * Math.sin(angle) / 2;
    }
}
