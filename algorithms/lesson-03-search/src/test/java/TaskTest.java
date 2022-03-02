import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.task.AmazonTask;

public class TaskTest {
    @Test
    void amazonTask() {
        Assertions.assertEquals(AmazonTask.resolve(new int[] {1, 2, 3, 4, 5, 6, 8, 9}), 7);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {1, 2, 3, 4}), 5);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {2, 3, 4, 5}), 1);
        Assertions.assertEquals(AmazonTask.resolve(new int[] {}), 1);
    }
}
