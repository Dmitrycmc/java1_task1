package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void task2Test1() {
        Assertions.assertArrayEquals(MainClass.task2(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}), new int[]{1, 7});
    }

    @Test
    void task2Test2() {
        Assertions.assertArrayEquals(MainClass.task2(new int[]{4, 2, 8, 9, 5, 3, 3, 1, 7}), new int[]{2, 8, 9, 5, 3, 3, 1, 7});
    }

    @Test
    void task2Test3() {
        Assertions.assertArrayEquals(MainClass.task2(new int[]{4, 2, 8, 9, 5, 3, 3, 1, 4}), new int[]{});
    }

    @Test
    void task2Test4() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            MainClass.task2(new int[]{7, 2, 8, 9, 5, 3, 3, 1, 8});
        });
    }

    @Test
    void task2Test5() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            MainClass.task2(new int[]{});
        });
    }

    @Test
    void task2Test6() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            MainClass.task2(null);
        });
    }

    @Test
    void task3Test1() {
        Assertions.assertTrue(MainClass.task3(new int[]{1, 2, 3, 5}));
    }

    @Test
    void task3Test2() {
        Assertions.assertTrue(MainClass.task3(new int[]{0, 2, 3, 4}));
    }

    @Test
    void task3Test3() {
        Assertions.assertFalse(MainClass.task3(new int[]{3, 2, 3, 5}));
    }

    @Test
    void task3Test4() {
        Assertions.assertFalse(MainClass.task3(new int[]{}));
    }

    @Test
    void task3Test5() {
        Assertions.assertThrows(RuntimeException.class, () -> {
        MainClass.task3(null);
    });
    }
}
