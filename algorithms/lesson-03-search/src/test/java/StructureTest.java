import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.Queue;
import ru.geekbrains.structure.Stack;

import java.util.LinkedList;
import java.util.List;

public class StructureTest {
    @Test
    void stack() {
        Stack<Integer> stack = new Stack<>();

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
        Queue<Integer> stack = new Queue<>();

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
}
