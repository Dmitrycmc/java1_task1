package ru.geekbrains;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structures.MyArrayList;
import ru.geekbrains.structures.MyLinkedList;
import ru.geekbrains.structures.MyList;

public class Tasks {
    private void testList(MyList<Integer> list) {
        Assertions.assertEquals(list.length(), 0);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{});

        list.push(1);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{1});

        Assertions.assertEquals(list.pop(), 1);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{});

        list.push(1);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{1});

        Assertions.assertEquals(list.shift(), 1);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{});

        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.push(5);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{1, 2, 3, 4, 5});

        Assertions.assertEquals(list.pop(), 5);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{1, 2, 3, 4});

        list.unshift(9);
        list.unshift(8);
        list.unshift(7);
        list.unshift(6);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{6, 7, 8, 9, 1, 2, 3, 4});

        Assertions.assertEquals(list.shift(), 6);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{7, 8, 9, 1, 2, 3, 4});

        Assertions.assertEquals(list.length(), 7);

        for (int i = 0; i < 10; i++) {
            list.push(123);
        }
        Assertions.assertEquals(list.length(), 17);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{7, 8, 9, 1, 2, 3, 4, 123, 123, 123, 123, 123, 123, 123, 123, 123, 123});

        list.clear();
        Assertions.assertEquals(list.length(), 0);
        Assertions.assertArrayEquals(list.toArray(), new Integer[]{});
    }
    @Test
    void MyLinkedList() {
        MyList<Integer> list = new MyLinkedList<>();

        testList(list);
    }

    @Test
    void MyArrayList() {
        MyList<Integer> list = new MyArrayList<>();

        testList(list);
    }
}
