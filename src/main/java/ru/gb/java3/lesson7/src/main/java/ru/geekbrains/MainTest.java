package ru.geekbrains;

import ru.geekbrains.test.AfterSuite;
import ru.geekbrains.test.BeforeSuite;
import ru.geekbrains.test.Test;

public class MainTest {
    @BeforeSuite
    void beforeSuite() {
        System.out.println("beforeSuite");
    }

    @AfterSuite
    void afterSuite() {
        System.out.println("afterSuite");
    }

    @Test()
    void Test1() {
        System.out.println("Test1, priority = 1");
    }

    @Test(priority = 4)
    void Test2() {
        System.out.println("Test2, priority = 4");
    }

    @Test(priority = 3)
    void Test3() {
        System.out.println("Test3, priority = 1");
    }

    @Test(priority = 10)
    void Test4() {
        System.out.println("Test4, priority = 10");
    }

    @Test(priority = 4)
    void Test5() {
        System.out.println("Test5, priority = 4");
    }
}
