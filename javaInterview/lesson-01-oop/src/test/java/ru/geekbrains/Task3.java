package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.entities.Circle;
import ru.geekbrains.entities.Person;
import ru.geekbrains.entities.Square;
import ru.geekbrains.entities.Triangle;
import ru.geekbrains.interfaces.Figure;

import java.sql.Array;
import java.util.Arrays;

public class Task3 {
    @Test
    void figuresTest() {
        Figure[] figures = {
                new Circle(1),
                new Square(1),
                new Triangle(2, 1, Math.PI / 2),
                new Circle(2),
                new Square(3),
                new Triangle(2, 4, Math.PI / 2)
        };

        Assertions.assertArrayEquals(
                Arrays.stream(figures).mapToDouble(Figure::getSquare).toArray(),
                new double[] {Math.PI, 1, 1, 4 * Math.PI, 9, 4}
        );
    }
}
