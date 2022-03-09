import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.task.Pow;
import ru.geekbrains.task.Thief;
import ru.geekbrains.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {
    @Test
    void pow() {
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

    @Test
    void thief() {
        List<Thief.Item> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Thief.Item(
                    Integer.toString(i),
                    MathUtils.randomInt(50, 10000),
                    MathUtils.randomInt(0, 10000)
            ));
        }

        for (Thief.Item item : list) {
            System.out.println(item);
        }

        System.out.println(Thief.resolve(list, 20000));
    }
}
