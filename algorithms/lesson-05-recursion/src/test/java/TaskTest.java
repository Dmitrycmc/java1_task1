import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.task.Pow;

public class TaskTest {
    @Test
    void stringReverse() {
        for (int i = -4; i <= 4; i++) {
            for (int j = -4; j <= 4 ; j++) {
                if (i == 0 && j < 0) {
                    int finalI = i;
                    int finalJ = j;
                    Assertions.assertThrows(IllegalArgumentException.class, () -> {
                        Pow.resolve(finalI, finalJ);
                    });
                } else {
                    Assertions.assertEquals((int)Math.pow(i, j), Pow.resolve(i, j));
                }
            }
        }
    }
}
