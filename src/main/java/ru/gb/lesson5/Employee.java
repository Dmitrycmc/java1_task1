package ru.gb.lesson5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public int getAge() {
        return age;
    }

    public Employee(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("Employee:");
        System.out.println("Full name:" + fullName);
        System.out.println("Position:" + position);
        System.out.println("Email:" + email);
        System.out.println("Phone:" + phone);
        System.out.println("Salary:" + salary);
        System.out.println("Age:" + age);
        System.out.println();
    }
}
