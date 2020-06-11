package ru.gb.java1.lesson5;

public class MainClass {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(
                        "Ivanov Ivan", "Engineer", "ivanov@email.ru",
                        "892312312", 120000, 27
                ),
                new Employee(
                        "Petrov Petr", "Front-end developer", "petrov@email.ru",
                        "891232312", 120000, 35
                ),
                new Employee(
                        "Romanov Roman", "Back-end developer", "romanov@email.ru",
                        "898912341", 120000, 45
                ),
                new Employee(
                        "Fyodorov Fyodor", "Sales", "fyodorov@email.ru",
                        "892377586", 120000, 50
                ),
                new Employee(
                        "Sidorov Sidr", "SEO", "sidorov@email.ru",
                        "892374125", 120000, 40
                )
        };

        for (Employee e : employees) {
            if (e.getAge() > 40) {
                e.printInfo();
            }
        }
    }
}


