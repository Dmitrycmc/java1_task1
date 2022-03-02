import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.task.StringReverse;

public class TaskTest {
    @Test
    void stringReverse() {
        Assertions.assertEquals(StringReverse.resolve("123456"), "654321");
        Assertions.assertEquals(StringReverse.resolve(""), "");
    }
}
