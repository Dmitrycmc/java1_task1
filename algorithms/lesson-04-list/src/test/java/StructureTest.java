import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.Deck;
import ru.geekbrains.structure.Queue;
import ru.geekbrains.structure.Stack;

import java.util.Deque;

public class StructureTest {
    @Test
    void stack() {
        Stack<Integer> stack = new Deck<>();

        Assertions.assertNull(stack.pop());

        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assertions.assertEquals(stack.pop(), 3);
        Assertions.assertEquals(stack.pop(), 2);

        stack.push(4);

        Assertions.assertEquals(stack.pop(), 4);
        Assertions.assertEquals(stack.pop(), 1);
        Assertions.assertNull(stack.pop());
    }

    @Test
    void queue() {
        Queue<Integer> stack = new Deck<>();

        Assertions.assertNull(stack.shift());

        stack.push(1);
        stack.push(2);
        stack.push(3);


        Assertions.assertEquals(stack.shift(), 1);
        Assertions.assertEquals(stack.shift(), 2);

        stack.push(4);

        Assertions.assertEquals(stack.shift(), 3);
        Assertions.assertEquals(stack.shift(), 4);
        Assertions.assertNull(stack.shift());
    }

    @Test
    void dequeRemove() {
        Deck<Integer> deque = new Deck<>();

        Assertions.assertEquals(deque.toString(), "");

        deque.push(2);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        deque.push(2);
        deque.push(1);
        deque.push(2);

        Assertions.assertEquals(deque.toString(), "2 <-> 2 <-> 3 <-> 4 <-> 2 <-> 1 <-> 2");


        Assertions.assertTrue(deque.remove(3));
        Assertions.assertFalse(deque.remove(30));
        Assertions.assertEquals(deque.toString(), "2 <-> 2 <-> 4 <-> 2 <-> 1 <-> 2");

        Assertions.assertEquals(deque.removeAll(2), 4);
        Assertions.assertEquals(deque.toString(), "4 <-> 1");
    }

    @Test
    void dequeInsert() {
        Deck<Integer> deque = new Deck<>();

        Assertions.assertEquals(deque.toString(), "");

        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        deque.push(3);
        deque.push(2);
        deque.push(1);

        Assertions.assertEquals(deque.toString(), "1 <-> 2 <-> 3 <-> 4 <-> 3 <-> 2 <-> 1");

        Assertions.assertTrue(deque.insertAfter(2, 0));
        Assertions.assertEquals(deque.toString(), "1 <-> 2 <-> 0 <-> 3 <-> 4 <-> 3 <-> 2 <-> 1");

        Assertions.assertTrue(deque.insertBefore(2, 9));
        Assertions.assertFalse(deque.insertBefore(20, 9));
        Assertions.assertEquals(deque.toString(), "1 <-> 9 <-> 2 <-> 0 <-> 3 <-> 4 <-> 3 <-> 2 <-> 1");
    }
}
