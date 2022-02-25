import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.Queue;
import ru.geekbrains.structure.Stack;
import ru.geekbrains.task.AmazonTask;
import ru.geekbrains.task.StringReverse;

import java.util.LinkedList;
import java.util.List;

public class TaskTest {
    @Test
    void stringReverse() {
        Assertions.assertEquals(StringReverse.resolve("123456"), "654321");
        Assertions.assertEquals(StringReverse.resolve(""), "");
    }

    @Test
    void amazonTask() {
        Assertions.assertEquals(AmazonTask.resolve(new int[] {1, 2, 3, 4, 5, 6, 8, 9}), 7);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {1, 2, 3, 4}), 5);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {2, 3, 4, 5}), 1);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {}), 1);
    }
}
