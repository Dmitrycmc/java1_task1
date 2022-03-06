package ru.geekbrains.task;

// Написать программу по возведению числа в степень с помощью рекурсии.
public class Pow {
    public static int resolve(int n, int p) {
        if (p > 0) {
            return resolve(n, p - 1) * n;
        }
        if (p < 0) {
            if (n == 0) {
                throw new IllegalArgumentException("Can not pow " + n + " in " + p);
            }
            return resolve(n, p + 1) / n;
        }
        return 1;
    }
}
