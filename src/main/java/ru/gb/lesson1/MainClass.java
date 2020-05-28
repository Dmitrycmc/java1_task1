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

    /*
        2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
    */
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

    /*
        3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
        где a, b, c, d – входные параметры этого метода;
    */
    private static float task3(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    /*
        4. Написать метод, принимающий на вход два числа,
        и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
        если да – вернуть true, в противном случае – false;
    */
    private static boolean task4(float a, float b) {
        final float sum = a + b;
        return 10 <= sum && sum <= 20;
    }

    /*
        5. Написать метод, которому в качестве параметра передается целое число,
        метод должен напечатать в консоль положительное ли число передали, или отрицательное;
        Замечание: ноль считаем положительным числом.
    */
    private static void task5(int a) {
        if (a >= 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }

    /*
        6. Написать метод, которому в качестве параметра передается целое число,
        метод должен вернуть true, если число отрицательное;
    */
    private static boolean task6(int a) {
        return a < 0;
    }

    /*
        7. Написать метод, которому в качестве параметра передается строка,
        обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    */
    private static void task7(String username) {
        System.out.println("Hello, " + username + "!");
    }

    /*
        8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
        Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    */
    private static void task8(int year) {
        boolean leap = (year % 400 == 0) || (year % 4 == 0) && (year % 100 > 0);
        System.out.println(year + " is " + (leap ? "" : "not") + "leap");
    }
}


