import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.Queue;
import ru.geekbrains.structure.Stack;
import ru.geekbrains.task.StringReverse;

import java.util.LinkedList;
import java.util.List;

public class TaskTest {
    @Test
    void stringReverse() {
        Assertions.assertEquals(StringReverse.resolve("123456"), "654321");
        Assertions.assertEquals(StringReverse.resolve(""), "");
    }
}
