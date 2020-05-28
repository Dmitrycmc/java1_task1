package ru.gb.lesson1;

public class MainClass {
    public static void main(String[] args) {
        task2();
        System.out.println(task3(5, 1.5f, -1, 0.6f));
        System.out.println(task4(10, 20));
        task5(0);
        System.out.println(task6(-1));
        task7("Dima");
        task8(2020);
    }

    private static void task2() {
        byte a = -120;
        short b = 30000;
        int m = 2000000000;
        long n = 50000000000000L;

        float f = 0.4f;
        double d = 0.8;

        char c = 'c';
        String str = "string";

        boolean flag = false;
    }

    private static float task3(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    private static boolean task4(float a, float b) {
        final float sum = a + b;
        return 10 <= sum && sum <= 20;
    }

    private static void task5(int a) {
        final float sign = Math.signum(a);
        if (sign >= 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }

    private static boolean task6(int a) {
        return a < 0;
    }

    private static void task7(String username) {
        System.out.println("Hello, " + username + "!");
    }

    private static void task8(int year) {
        boolean leap = (year % 400 == 0) || (year % 4 == 0) && (year % 100 > 0);
        System.out.println(year + " is " + (leap ? "" : "not") + "leap");
    }
}


