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

        List<Integer> results = new LinkedList<>();

        results.add(stack.pop());

        stack.push(1);
        stack.push(2);
        stack.push(3);


        results.add(stack.pop());
        results.add(stack.pop());

        stack.push(4);

        results.add(stack.pop());
        results.add(stack.pop());
        results.add(stack.pop());

        Assertions.assertEquals(results.toString(), "[null, 3, 2, 4, 1, null]");
    }

    @Test
    void queue() {
        Queue<Integer> stack = new Queue<>();

        List<Integer> results = new LinkedList<>();

        results.add(stack.shift());

        stack.push(1);
        stack.push(2);
        stack.push(3);


        results.add(stack.shift());
        results.add(stack.shift());

        stack.push(4);

        results.add(stack.shift());
        results.add(stack.shift());
        results.add(stack.shift());

        Assertions.assertEquals(results.toString(), "[null, 1, 2, 3, 4, null]");
    }

    @Test
    void deck() {
    }
}
